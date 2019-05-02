package xyz.frt.basesdk2.util;

import lombok.extern.slf4j.Slf4j;
import xyz.frt.basesdk2.websocket.WebSocketServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileListener {

    private static ExecutorService fixedThreadPool = Executors.newCachedThreadPool();

    private String logPath;

    private String logName;

    public FileListener(String logPath, String logName) {
        this.logPath = logPath;
        this.logName = logName;
    }

    public void onFileContentChanged() throws IOException {
        if (BaseUtils.isNullOrEmpty(logPath)) {
            throw new RuntimeException("File Path cannot be empty!");
        }
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(logPath);
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
        fixedThreadPool.execute(new Listener(watchService, logPath + File.separator + logName));
    }

}


@Slf4j
class Listener implements Runnable {

    private WatchService watchService;

    private String logPath;

    Listener(WatchService watchService, String logPath) {
        this.watchService = watchService;
        this.logPath = logPath;
    }

    @Override
    public void run() {
        StringBuilder sb = null;
        BufferedReader is = null;
        try {
            while (true) {
                WatchKey watchKey = watchService.take();
                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                for (WatchEvent<?> event: watchEvents) {
                    if (StandardWatchEventKinds.ENTRY_MODIFY.name().equals(event.kind().name())) {
                        is = new BufferedReader(new FileReader(new File(logPath)));
                        sb = new StringBuilder();
                        String line = "";
                        while((line = is.readLine()) != null) {
                            sb.append(line).append("\n");
                        }
                        WebSocketServer.sendInfo("10", sb.toString());
                    }
                }
                watchKey.reset();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                watchService.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

