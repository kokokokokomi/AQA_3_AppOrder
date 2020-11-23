package ru.netology;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardApplicationTest {

    @Test
    void shouldConfirmRequest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Шамиль Газизов");
        $("[data-test-id=phone] input").setValue("+79005553535");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotConfirmRequestIfInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Shamil Gazizov");
        $("[data-test-id=phone] input").setValue("+79005553535");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
