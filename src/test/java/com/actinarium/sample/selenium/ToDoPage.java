package com.actinarium.sample.selenium;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

/**
 * <p></p>
 *
 * @author Paul Danyliuk
 * @version $Id$
 */
public class ToDoPage {

    @FindBy(css = "input.new-todo")
    private TextInput input;

    @FindBy(css = "ul.todo-list > li")
    private List<ToDoRow> rows;

    public ToDoPage(WebDriver driver) {
        PageFactory.initElements(
                new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)),
                this
        );
    }

    public void addItem(String text) {
        input.sendKeys(text, Keys.ENTER);
    }

    public List<ToDoRow> getRows() {
        return rows;
    }

}
