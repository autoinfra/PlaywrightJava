package utilities.customAnnotations.AzureDevOps;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AzureDevOpsIssue {
    String AzIssueID() default "";
    String AzIssueType() default "Bug";
}
