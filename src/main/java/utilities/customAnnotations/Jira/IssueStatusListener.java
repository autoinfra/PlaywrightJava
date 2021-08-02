package utilities.customAnnotations.Jira;

import org.testng.*;

public class IssueStatusListener implements IInvokedMethodListener {
    public static final String BACKLOG ="Backlog";
    public static final String DEVELOPMENT_STARTED= "Development Started";
    public static final String str_Code_Review= "Code Review";
    public static final String str_DEVELOPMENT_IN_PROGRESS="Development In Progress";
    public static final String str_READY_TO_TEST ="Ready To Test";
    public static final String TESTING_IN_PROGRESS ="Test In Progress";
    public static final String DONE ="DONE";

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
 /*       JiraIssue issueid = method.getTestMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(JiraIssue.class);


        if (null != issueid) {
            if (jiraApiCall.getWorkItemStatus(issueid.IssueID()) != str_READY_TO_TEST) {
                   // testResult.setStatus(ITestResult.SKIP);
                    throw new SkipException("Skipping this due to Open Issue - " + issueid.IssueID());


            }
           // return;
        }*/
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        JiraIssue issueid = method.getTestMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(JiraIssue.class);

        if (null != issueid) {
            if(str_READY_TO_TEST.equalsIgnoreCase(jiraApiCall.getWorkItemStatus(issueid.IssueID()))){
                switch(testResult.getStatus()){
                    case ITestResult.FAILURE:
                        // no need to fail as we might have expected this already.
                        testResult.setStatus(ITestResult.SKIP);
                        break;
                    case ITestResult.SUCCESS:
                        // TO-DO Write Code to Move the Jira Ticket from Ready to Test to Tested/Done
                        jiraApiCall.AddScreenshot(issueid.IssueID(),"HtmlReports/Extent/2021-Jul-28_17_48/Search-For-Selenium17_48_16.png");
                        break;
                }
            }
        }
    }


}
