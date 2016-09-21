package com.actinarium.sample.selenium;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextBlock;

/**
 * <p></p>
 *
 * @author Paul Danyliuk
 * @version $Id$
 */
public class ToDoRow extends HtmlElement {

    @FindBy(className = "toggle")
    private CheckBox toggle;

    @FindBy(tagName = "label")
    private TextBlock label;

    @FindBy(className = "destroy")
    private Button destroy;

    public CheckBox getToggle() {
        return toggle;
    }

    public String getLabel() {
        return label.getText();
    }

    public void clickDestroy() {
        label.getWrappedElement().click();
        destroy.click();
    }
}
