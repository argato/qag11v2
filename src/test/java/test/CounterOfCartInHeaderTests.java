package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

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
@Story("Отображение счетчика корзины в хедере")
@Tag("smoke")
public class CounterOfCartInHeaderTests extends TestBase {

  PopUpHelper popUpHelper = new PopUpHelper();

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Отображение счетчика при добавлении первого товара")
  @Severity(SeverityLevel.NORMAL)
  void showingCounterTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";
    popUpHelper.popupCityClose();
    $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
    $(".header-sub .search button.icon-search").click();
    $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));

    SelenideElement resultItem = $$(".product-card")
        .findBy(Condition.text(expectedItemName))
        .shouldHave(Condition.exist);

    resultItem.$(".service_btn_buy_main:not(.q1)").click();
    $(".search span.icon__count_pump").shouldHave(Condition.text("1"));
  }

  @Test
  @DisplayName("Отображение счетчика при добавлении двух единиц одного товара")
  @Severity(SeverityLevel.NORMAL)
  void showingCounterTwoItemsTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";
    popUpHelper.popupCityClose();
    $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
    $(".header-sub .search button.icon-search").click();
    $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));

    SelenideElement resultItem = $$(".product-card")
        .findBy(Condition.text(expectedItemName))
        .shouldHave(Condition.exist);

    resultItem.$(".service_btn_buy_main:not(.q1)").click();
    $(".search span.icon__count_pump").shouldHave(Condition.text("1"));

    resultItem.$("div.product-card__content > .product-card__action .btn-product_add").click();
    $(".search span.icon__count_pump").shouldHave(Condition.text("1"));
  }

  @Test
  @DisplayName("Отображение счетчика, при нажатии \"Купить в один клик\".")
  @Severity(SeverityLevel.NORMAL)
  void buyOneClickShowingCounterTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";
    popUpHelper.popupCityClose();
    $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
    $(".header-sub .search button.icon-search").click();
    $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));

    SelenideElement resultItem = $$(".product-card")
        .findBy(Condition.text(expectedItemName))
        .shouldHave(Condition.exist);

    resultItem.scrollTo()
              .$("div.product-card__content > .product-card__buynow a[data-entity='basket-checkout-button']")
              .click();
    $(".search span.icon__count_pump").shouldHave(Condition.text("1"));
  }
}
