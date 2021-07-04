package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.CollectionCondition;
import helper.PopUpHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("arina_ng")
@Feature("Поиск")
@Story("Поиск валидных значений")
@Tag("smoke")
public class SearchTests extends TestBase {

  PopUpHelper popUpHelper = new PopUpHelper();

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Строка в нижнем регистре")
  @Severity(SeverityLevel.CRITICAL)
  void validStringSearchTest() {
    popUpHelper.popupCityClose();
    $(".header-sub .search [name='q']").setValue("матрас");
    $(".header-sub .search button.icon-search").click();
    $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));
  }
}