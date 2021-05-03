package org.AutoInfra;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.SneakyThrows;

public class SearchPage {

    private Page page;
    public SearchPage(Page page) {
        this.page=page;
    }

    public void navigate() {
        page.navigate("https://duckduckgo.com/");
    }

    public void doSearch(String Keyword)
    {
        page.waitForSelector("input#search_form_input_homepage",new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.fill("input#search_form_input_homepage",Keyword);
        page.click("#search_button_homepage");

    }
    @SneakyThrows
    public void goToVideos()
    {
        page.waitForSelector("text=Videos",new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("text=Videos");
        Thread.sleep(5000);
    }

    public int printResult()
    {
        //page.waitForSelector("tile--vid",new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        return page.querySelectorAll(".tile.tile--c--w.tile--vid.has-detail.opt--t-xxs").size();

    }

}
