package helper;

import static com.codeborne.selenide.Selenide.$;

public class PopUpHelper {

  public void popupCityClose() {
    $(".popup-city-accept .popup__close ").click();
  }
}
