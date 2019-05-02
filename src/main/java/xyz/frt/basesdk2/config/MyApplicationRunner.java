package xyz.frt.basesdk2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import xyz.frt.basesdk2.util.FileListener;

@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Value("${app.config.logPath}")
    private String logPath;

    @Value("${app.config.logName}")
    private String logName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileListener fileListener = new FileListener(logPath, logName);
        fileListener.onFileContentChanged();
        log.info("File Listener Initial complete...");
    }

}
