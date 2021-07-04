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
@Story("Отображение стоимости в корзине в хедере")
@Tag("smoke")
public class PriceOfCartInHeaderTests extends TestBase {

  PopUpHelper popUpHelper = new PopUpHelper();

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Отображение счетчика при добавлении первого товара")
  @Severity(SeverityLevel.NORMAL)
  void showingPriceTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";
    popUpHelper.popupCityClose();
    $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
    $(".header-sub .search button.icon-search").click();
    $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));

    SelenideElement resultItem = $$(".product-card")
        .findBy(Condition.text(expectedItemName))
        .shouldHave(Condition.exist);

    resultItem.$(".service_btn_buy_main:not(.q1)").click();
    $(".search span.header-basket-price").shouldHave(Condition.text("от 89 ₽"));
  }

  @Test
  @DisplayName("Отображение стоимости при добавлении двух единиц одного товара")
  @Severity(SeverityLevel.NORMAL)
  void showingPriceTwoItemsTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";
    popUpHelper.popupCityClose();
    $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
    $(".header-sub .search button.icon-search").click();
    $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));

    SelenideElement resultItem = $$(".product-card")
        .findBy(Condition.text(expectedItemName))
        .shouldHave(Condition.exist);

    resultItem.$(".service_btn_buy_main:not(.q1)").click();
    resultItem.$("div.product-card__content > .product-card__action .btn-product_add").click();
    $(".search span.header-basket-price").shouldHave(Condition.text("от 178 ₽"));
  }

}
