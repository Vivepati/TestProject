package com.selenium.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtils
{
  private static String sEncoding = System.getProperty("epm.encoding", "").trim();

  public static Logger getLogger(String className, String testName)
    throws IOException
  {
    Logger logger = null;
    String basePathForLogs = new File(System.getProperty("user.dir")).getAbsolutePath();
    if (basePathForLogs.contains("build")) {
      basePathForLogs = new File(System.getProperty("user.dir")).getParent();
    }
    basePathForLogs = basePathForLogs + File.separator + "logs" + File.separator;
    FileHandler handler = null;
    System.out.println(">>> Log File with Path :  " + basePathForLogs + className + "." + testName + "_result.log");

    logger = Logger.getLogger(className + "." + testName + "_result.log");
    handler = new FileHandler(basePathForLogs + className + "." + testName + "_result.log", true);
    if (!sEncoding.equals("")) {
      handler.setEncoding(sEncoding);
    }

    logger.addHandler(handler);
    logger.setLevel(Level.ALL);
    logger.setUseParentHandlers(false);

    handler.setFormatter(new SimpleFormatter());
    return logger;
  }

  public static Logger getLogger(String className)
    throws IOException
  {
    Logger logger = null;
    String basePathForLogs = new File(System.getProperty("user.dir")).getAbsolutePath();
    if (basePathForLogs.contains("build")) {
      basePathForLogs = new File(System.getProperty("user.dir")).getParent();
    }
    basePathForLogs = basePathForLogs + File.separator + "logs" + File.separator;
    FileHandler handler = null;

    logger = Logger.getLogger(className + "_result.log");
    handler = new FileHandler(basePathForLogs + className + "_result.log", true);
    if (!sEncoding.equals("")) {
      handler.setEncoding(sEncoding);
    }

    logger.addHandler(handler);
    logger.setLevel(Level.ALL);
    logger.setUseParentHandlers(false);

    handler.setFormatter(new SimpleFormatter());
    return logger;
  }

  public static Logger getLogger()  throws IOException   {
    Logger logger = null;
    String basePathForLogs = new File(System.getProperty("user.dir")).getAbsolutePath();
    if (basePathForLogs.contains("build")) {
      basePathForLogs = new File(System.getProperty("user.dir")).getParent();
    }
    basePathForLogs = basePathForLogs + File.separator + "logs" + File.separator;

    FileHandler handler = null;
    String className = new Throwable().fillInStackTrace().getStackTrace()[1].getClassName();
    String testName = new Throwable().fillInStackTrace().getStackTrace()[1].getMethodName();

    System.out.println(">>> Log File with Path :  " + basePathForLogs + className + "." + testName + "_result.log");
    logger = Logger.getLogger(className + "." + testName + "_result.log");
    handler = new FileHandler(basePathForLogs + className + "." + testName + "_result.log", true);
    if (!sEncoding.equals("")) {
      handler.setEncoding(sEncoding);
    }

    logger.addHandler(handler);
    logger.setLevel(Level.ALL);

    logger.setUseParentHandlers(false);

    handler.setFormatter(new SimpleFormatter());
    return logger;
  }

  public static void releaselogger(Logger logger) throws Exception
  {
    Handler[] handler = logger.getHandlers();
    for (int i = 0; i < handler.length; i++) {
      System.out.println(">>> Removing handler.....");
      logger.removeHandler(handler[i]);
      handler[i].close();
    }
  }

  public static Logger setUpcLogger() throws Exception
  {
    String basePathForLogs = null;
    String currDir = new File(System.getProperty("user.dir")).getAbsolutePath();
    if (currDir.contains("build")) {
      currDir = new File(System.getProperty("user.dir")).getParent();
    }
    basePathForLogs = currDir + File.separator + "logs" + File.separator;

    Logger clogger = getLogger(new Throwable().getStackTrace()[1].getClassName());
    clogger.info("Starting : " + new Throwable().getStackTrace()[1].getClassName());
    return clogger;
  }

  @Deprecated
  public static void logStart(Logger logger, String testName)
  {
    logger.info(testName + "::Start");
  }

  @Deprecated
  public static void logEnd(Logger logger, String testName) {
    logger.info(testName + "::End");
  }

  public static String getStackTraceForLogger(Throwable t)
  {
    StringWriter strwtr = new StringWriter();
    PrintWriter prnwtr = new PrintWriter(strwtr, true);
    t.printStackTrace(prnwtr);
    String str = strwtr.toString();
    prnwtr.flush();
    strwtr.flush();
    return str;
  }
}
