package xyz.frt.basesdk2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.basesdk2.common.AppContext;
import xyz.frt.basesdk2.common.JsonResult;

import java.io.*;

@Slf4j
@RestController
public class CommonController {

    @Value("${app.config.logPath}")
    private String logPath;

    @Value("${app.config.logName}")
    private String logName;

    @Value("${app.config.shell}")
    private String shellPath;

    @Value("${app.config.urlPath}")
    private String urlPath;

    @GetMapping("/siege/{c}")
    public JsonResult execSiegeShell(@PathVariable Integer c) {
        BufferedWriter writer = null;
        File bash = new File(shellPath);
        try {
            writer = new BufferedWriter(new FileWriter(bash));
            if (c > 0 && c < 10000) {
                File file = new File(urlPath);
                String command = "siege -H 'Authorization:"
                        + AppContext.getCurrentUser().getToken()
                        + "' -H 'Content-Type:application/json' -c "
                        + c + " -r 1 -f " + file.getAbsolutePath();
                command = "script -c \"" + command + "\" " + logPath + File.separator + logName;
                String content = "#!/bin/bash\n" + command;
                writer.write(content);
                log.debug(command);
                return JsonResult.success("执行成功");
            } else {
                return JsonResult.error("请输入合法的参数(0 < c < 10000)");
            }
        } catch (IOException e) {
            return JsonResult.error(e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    int res = Runtime.getRuntime().exec(bash.getAbsolutePath()).waitFor();
                    log.debug(res + "");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
