package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class DebitCardApplicationTest {

    @BeforeEach
    void shouldOpenBrowser() { open("http://localhost:9999"); }

    @Test
    void shouldConfirmRequest() {
        $("[data-test-id=name] input").setValue("Шамиль Газизов");
        $("[data-test-id=phone] input").setValue("+79005553535");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotConfirmRequestIfIncorrectName() {
        $("[data-test-id=name] input").setValue("Shamil Gazizov");
        $("[data-test-id=phone] input").setValue("+79005553535");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Фамилия и имя указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotConfirmRequestIfIncorrectPhone() {
        $("[data-test-id=name] input").setValue("Сергей Иванов");
        $("[data-test-id=phone] input").setValue("+7904222222");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotConfirmRequestIfNotSetCheckbox() {
        $("[data-test-id=name] input").setValue("Шамиль Газизов");
        $("[data-test-id=phone] input").setValue("+79005553535");
        $("[data-test-id=agreement]").exists();
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
