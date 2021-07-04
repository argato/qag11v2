package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static test.TestBase.BASE_URL;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
@Feature("Корзина")
@Story("Переход в корзину")
@Tag("smoke")
public class GoToCart {

  PopUpHelper popUpHelper = new PopUpHelper();

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Переход в корзину при нажатии \"Купить в один клик.\"")
  @Severity(SeverityLevel.NORMAL)
  void buyOneClickGoToCartTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";
    popUpHelper.popupCityClose();
    step("Выполнить поиск", (step) -> {
      $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
      $(".header-sub .search button.icon-search").click();
      $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));
    });
    step("Нажать на \"Купить в один клик\" на одном из результатов поиска", (step) -> {
      SelenideElement resultItem = $$(".product-card")
          .findBy(Condition.text(expectedItemName))
          .shouldHave(Condition.exist);

      resultItem.scrollTo()
                .$("div.product-card__content > .product-card__buynow a[data-entity='basket-checkout-button']")
                .click();
    });
    step("Проверить, что произошел переход в корзину к шагу выбора аптеки", (step) -> {
      $(".page-content__basket-inn h1").shouldHave(
          Condition.text("Выберите аптеку, в которой хотите забрать заказ"));
    });
  }
}
