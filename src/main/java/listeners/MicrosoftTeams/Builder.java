package listeners.MicrosoftTeams;

import java.util.Arrays;
import java.util.List;

import static listeners.MicrosoftTeams.AdaptiveCardReqBody.*;

public class Builder {

    List<ItemsItem> TestCaseName;
    List<ItemsItem> TestCaseID;
    List<ItemsItem> TestResult;
    List<FactsItem> FactsItems;

    public Builder(List<ItemsItem> testCaseName, List<ItemsItem> testCaseID, List<ItemsItem> testResult,List<FactsItem> factsItems ) {
        TestCaseName = testCaseName;
        TestCaseID=testCaseID;
        TestResult=testResult;
        FactsItems=factsItems;

    }

    public AdaptiveCardReqBody reqBody(){
        return builder()
                .type("message")
                .attachments(Arrays.asList(attachments()))
                .build();
    }

    public AttachmentsItem attachments(){

        return  AttachmentsItem.builder()
                .content(content())
                .contentType("application/vnd.microsoft.card.adaptive").build();

    }

    public Content content(){
        return Content.builder()
                .type("AdaptiveCard")
                .body(Arrays.asList(banner(),factSetBody(),headings(),testcases(), buttons()))
                .build();
    }

    public BodyItem banner(){

        ItemsItem itemsItem1 = ItemsItem.builder()
                .type("TextBlock")
                .size("Large")
                .weight("Bolder")
                .text("**Automation Test Results**")
                .wrap(true)
                .style("heading")
                .build();

        ItemsItem itemsItem2 = ItemsItem.builder()
                .type("Image")
                .url("https://upload.wikimedia.org/wikipedia/en/thumb/e/ea/Kongsberg_Gruppen_logo.svg/1200px-Kongsberg_Gruppen_logo.svg.png")
                .altText("Failed")
                .height("50px")
                .build();

        ColumnsItem columnsItem1 = ColumnsItem.builder()
                .type("Column")
                .width("stretch")
                .items(Arrays.asList(itemsItem1)).build();

        ColumnsItem columnsItem2 = ColumnsItem.builder()
                .type("Column")
                .width("stretch")
                .items(Arrays.asList(itemsItem2)).build();



        ItemsItem itemsItem = ItemsItem.builder()
                .type("ColumnSet")
                .columns(Arrays.asList(columnsItem1,columnsItem2)).build();

       return  BodyItem.builder()
                .type("Container")
                .style("emphasis")
                .text("Test")
                .items(Arrays.asList(itemsItem))
                .build();


    }

    public BodyItem factSetBody(){


        return  BodyItem.builder()
                .type("FactSet")
                .spacing("Large")
                .facts(FactsItems)
                .build();
    }

    public BodyItem headings(){
        ItemsItem itemsItem1 = ItemsItem.builder()
                .type("TextBlock")
                .text("TC ID")
                .weight("Bolder")
                .wrap(true)
                .build();

        ItemsItem itemsItem2 = ItemsItem.builder()
                .type("TextBlock")
                .text("TestCase Name")
                .weight("Bolder")
                .wrap(true)
                .build();

        ItemsItem itemsItem3 = ItemsItem.builder()
                .type("TextBlock")
                .text("Result")
                .weight("Bolder")
                .color("dark")
                .wrap(true)
                .build();

        ColumnsItem columnsItem1 = ColumnsItem.builder()
                .type("Column")
                .items(Arrays.asList(itemsItem1))
                .width("auto").build();

        ColumnsItem columnsItem2 = ColumnsItem.builder()
                .type("Column")
                .spacing("medium")
                .items(Arrays.asList(itemsItem2))
                .width("stretch").build();

        ColumnsItem columnsItem3 = ColumnsItem.builder()
                .type("Column")
                .items(Arrays.asList(itemsItem3))
                .width("auto").build();

        ItemsItem itemsItem = ItemsItem.builder()
                .type("ColumnSet")
                .columns(Arrays.asList(columnsItem1,columnsItem2,columnsItem3))
                .build();

        return  BodyItem.builder()
                .type("Container")
                .items(Arrays.asList(itemsItem))
                .build();
    }

    public BodyItem testcases(){

        ColumnsItem columnsItem1 = ColumnsItem.builder()
                .type("Column")
                .items(TestCaseID)
                .width("auto").build();

        ColumnsItem columnsItem2 = ColumnsItem.builder()
                .type("Column")
                .spacing("medium")
                .items(TestCaseName)
                .width("stretch").build();

        ColumnsItem columnsItem33 = ColumnsItem.builder()
                .type("Column")
                .spacing("medium")
                .items(TestResult)
                .width("auto").build();


        ItemsItem itemsItem = ItemsItem.builder()
                .type("ColumnSet").columns(Arrays.asList(columnsItem1,columnsItem2,columnsItem33)).build();

        return BodyItem.builder().type("Container")
                .items(Arrays.asList(itemsItem)).build();
    }

    public BodyItem errorDetails(){

        ItemsItem itemsItem1 = ItemsItem.builder()
                .type("TextBlock")
                .text("Error Details")
                .isSubtle(true)
                .wrap(true)
                .build();
ItemsItem itemsItem2 = ItemsItem.builder()
        .type("Container").items(Arrays.asList(itemsItem1)).build();

        return  BodyItem.builder()
                .type("Container")
                .id("ExceptionDetails")
                .isVisible(false)
                .items(Arrays.asList(itemsItem2))
                .build();
    }

    public BodyItem buttons(){
        ActionsItem actionsItem1 = ActionsItem.builder()
                .title("View Test Results")
                .type("Action.OpenUrl")
                .url("https://adaptivecards.io")
                .style("destructive")
                .build();

        ActionsItem actionsItem2 = ActionsItem.builder()
                .title("Raise Bug")
                .type("Action.OpenUrl")
                .url("https://adaptivecards.io")
                .style("destructive")
                .build();

        ItemsItem itemsItem = ItemsItem.builder()
                .type("ActionSet")
                .actions(Arrays.asList(actionsItem1,actionsItem2)).build();

        return  BodyItem.builder()
                .type("Container")
                //.isVisible(false)
                .items(Arrays.asList(itemsItem))
                .build();
    }


    public void arrows(){
        ////Column3
        SelectAction selectAction = SelectAction.builder().type("Action.ToggleVisibility")
                .targetElements(Arrays.asList("ExceptionDetails", "chevronDown1", "chevronUp1")).build();

        ItemsItem itemsItem3 = ItemsItem.builder()
                .type("Image")
                .id("chevronDown1")
                .url("https://adaptivecards.io/content/down.png")
                .width("20px")
                .altText("desc 12 collapsed").build();

        ItemsItem itemsItem4 = ItemsItem.builder()
                .type("Image")
                .id("chevronUp1")
                .url("https://adaptivecards.io/content/up.png")
                .width("20px")
                .isVisible(false)
                .altText("description total expanded").build();

        ColumnsItem columnsItem3 = ColumnsItem.builder()
                .type("Column")
                .spacing("Small")
                .selectAction(selectAction)
                .verticalContentAlignment("Center")
                .items(Arrays.asList(itemsItem3,itemsItem4))
                .width("auto").build();

    }
}
