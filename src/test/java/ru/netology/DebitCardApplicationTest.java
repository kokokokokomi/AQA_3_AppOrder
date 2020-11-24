package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardApplicationTest {

    @Test
    void shouldConfirmRequest() {
        open("http://localhost:9999");
        //SelenideElement form = $("[id=root]");
        $("[data-test-id='name'] input").setValue("Шамиль Газизов");
        $("[data-test-id=phone] input").setValue("+79005553535");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotConfirmRequestIfIncorrectName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Shamil Gazizov");
        $("[data-test-id=phone] input").setValue("+79005553535");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        //$("input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotConfirmRequestIfIncorrectPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Сергей Иванов");
        $("[data-test-id=phone] input").setValue("9042222222");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Укажите точно как в паспорте"));
    }

    @Test
    void shouldNotConfirmRequestIfIncorrectAll() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Sergey Ivanov");
        $("[data-test-id=phone] input").setValue("9042222222");
        $("[data-test-id=agreement]").exists();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
