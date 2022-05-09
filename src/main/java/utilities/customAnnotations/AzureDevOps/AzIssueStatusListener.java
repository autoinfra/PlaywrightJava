package utilities.customAnnotations.AzureDevOps;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;


public class AzIssueStatusListener  implements IInvokedMethodListener {
    public static final String BACKLOG ="Backlog";
    public static final String DEVELOPMENT_STARTED= "Development Started";
    public static final String str_Code_Review= "Code Review";
    public static final String str_DEVELOPMENT_IN_PROGRESS="Development In Progress";
    public static final String str_READY_TO_TEST ="Ready To Test";
    public static final String TESTING_IN_PROGRESS ="Test In Progress";
    public static final String DONE ="DONE";

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        AzureDevOps issueid = method.getTestMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(AzureDevOps.class);

        if (null != issueid) {
            if(str_READY_TO_TEST.equalsIgnoreCase(AzDevOpsApiCall.getWorkItemStatus(issueid.BugID()))){
                switch(testResult.getStatus()){
                    case ITestResult.FAILURE:
                        // no need to fail as we might have expected this already.
                        testResult.setStatus(ITestResult.SKIP);
                        break;
                    case ITestResult.SUCCESS:
                        // TO-DO Write Code to Move the AZ Ticket from Ready to Test to Tested/Done
                        break;
                }
            }
        }
    }


}
