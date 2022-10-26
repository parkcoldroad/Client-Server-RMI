package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Log {

  java.util.logging.Logger logger = java.util.logging.Logger.getLogger("DataLog");
  private static Log log;

  public static final String infoLog = "D:/java/Java-RMI/rmi-data/src/log/DataLog%g.log";

  private FileHandler infoFileHandler = null;

  private Log() {
    try {
      // path, append 방식으로 생성
      infoFileHandler = new FileHandler(infoLog, 200, 1000, true);
    } catch (SecurityException | IOException e) {
      e.printStackTrace();
    }

    infoFileHandler.setFormatter(new SimpleFormatter());
    infoFileHandler.setLevel(Level.INFO);
    logger.addHandler(infoFileHandler);
  }

  public static Log getLogger() {
    if (log == null) {
      log = new Log();
    }
    return log;
  }

  public void fine(String msg) {
    logger.info(msg);
  }
}
