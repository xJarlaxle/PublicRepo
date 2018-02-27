using System;
using System.IO;

namespace LoggerLib
{
    public class Logger
    {
        static string defaultPath = "C:\\Users\\NSCCStudent\\Desktop\\PROG2200\\Willett-Brian-w0280361\\BrianWillett_Assignment2_PROG2200\\LoggerLib\\bin\\Logs\\";
        string logFilePath = defaultPath;
        int countIt = 0;

        /// <summary>
        /// Creates a new log file for appending
        /// </summary>
        public void CreateNewLogFile() {
            if (countIt == 0) {
                string ext = "ChatLog-" + DateTime.Now.ToFileTime() + ".txt";
                logFilePath = Path.Combine(logFilePath, ext);
                AppendToLog("Client connected to server at 127.0.0.1 on port 13000");
            }
            else {
                string ext = "ChatLog-" + DateTime.Now.ToFileTime() + ".txt";
                logFilePath = Path.Combine(defaultPath, ext);
                AppendToLog("Client connected to server at 127.0.0.1 on port 13000");
            }
            countIt++;
        }

        /// <summary>
        /// Writes a string to the log file
        /// </summary>
        /// <param name="strLog">Chat string</param>
        public void AppendToLog(string strLog) {
            try {
                using (StreamWriter outputFile = File.AppendText(logFilePath)) {
                    outputFile.WriteLine(DateTime.Today.ToString("M/d/yy") + " " + DateTime.Now.ToString("h:mm:ss tt") + " - " + strLog);
                }
            }
            catch (DirectoryNotFoundException e) { }
            catch (ArgumentNullException e) { }
            catch (UnauthorizedAccessException e) { }
            catch (PathTooLongException e) { }
            catch (ArgumentException e) { }
            catch (NotSupportedException e) { }

        }

    }
}
