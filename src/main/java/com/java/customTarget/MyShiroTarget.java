package com.java.customTarget;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @author liufeng
 * @version [1.0 , 2017/9/12]
 */
public class MyShiroTarget extends SimpleTagSupport {
    private PageContext pageContext;
    private String name;

    public PageContext getPageContext() {
        return pageContext;
    }

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspFragment jf = this.getJspBody();
        //相当于jf.invoke(null);
        if (name.equals("123")) {
            jf.invoke(this.getJspContext().getOut());
        }
        //这里如果不想输出标签体内容的话，只需要不调用invoke方法即可
    }
}
