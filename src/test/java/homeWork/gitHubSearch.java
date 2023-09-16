package homeWork;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class gitHubSearch {
    @Test
    void searchOnSoftAssertions() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";

        // Открыть страницу Selenide в Github
        open("selenide/selenide");

        //Перейти в раздел Wiki проекта
        $("#wiki-tab").click();

        //Убедиться, что в списке страниц (Pages) есть страница SoftAssertions
        $(".js-wiki-sidebar-toggle-display [type='button']").click();
        $$(".js-wiki-sidebar-toggle-display li").last().preceding(0).shouldHave(text("SoftAssertions"));

        //Открыть страницу SoftAssertions, проверить что внутри есть пример кода для JUnit5
        $$(".js-wiki-sidebar-toggle-display li").last().preceding(0).$(".Truncate").click();
        $$(".markdown-body h4").last().sibling(0).$("pre").shouldHave
                (text("""
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                        @Test
                        void test() {
                        Configuration.assertionMode = SOFT;
                        open("page.html");

                        $("#first").should(visible).click();
                        $("#second").should(visible).click();
                        }
                        }
                        """)
                );
    }
}
