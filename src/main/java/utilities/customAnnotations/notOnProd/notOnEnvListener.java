package utilities.customAnnotations.notOnProd;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;
import java.util.function.Predicate;

public class notOnEnvListener implements IMethodInterceptor {
    ITestContext context;
    Predicate<IMethodInstance> isTestExecutingOnProduction = (tip) -> {
        return context.getSuite().getParameter("environment").equalsIgnoreCase("prod");
                };

    Predicate<IMethodInstance> isNotApplicableForProduction = (method) -> {
        return method.getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .isAnnotationPresent(NotOnProd.class);
    };

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> testMethods, ITestContext context) {
        this.context=context;
        //removeIf method returns a boolean if any of the item is removed
        boolean isTestRemoved = testMethods.removeIf(isNotApplicableForProduction.and(isTestExecutingOnProduction));

        //if any item is removed, then there is a chance that we might get execption due to missing dependencies.
        testMethods.stream()
                .map(IMethodInstance::getMethod)
                .forEach(method -> method.setIgnoreMissingDependencies(isTestRemoved));

        return testMethods;
    }
}
