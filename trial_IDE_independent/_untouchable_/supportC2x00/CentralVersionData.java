package _untouchable_.supportC2x00;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * Central "container" for version IDs.
 * Here, the version IDs are "greped" from the classes there are defined in.
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200606 #01
 */
public class CentralVersionData {
    
    // lab exam version ID
    public final static String centralLabExamVersionID = "2021/08/20 P1 LabExam1215 [v2.00]";                           //  ### <<<<====
    
    
    // test support routine collection (the "engine" ;-) version ID
    public final static String centralTestSupportVersionID = "2021/08/05 v2.02";
    
    
    // version ID of WPI computer
    public final static String centralWPIComputerVersionID = TestResultAnalyzer.wpiComputerVersionID;
    
    
    // version ID of configuration class/structure/possibilities
    public final static String centralTestConfigurationVersionID = Configuration.configurationVersionID;
    
    
    
    // "serialVersionUID": test result data base (format for serialization) version ID   - generally the "Demo and Reference lab exam" shall contain the latest version
    public final static long centralTestResultDataBaseRelatedSerialVersionUID = 2017_06_14_0001L;
    
}//class
