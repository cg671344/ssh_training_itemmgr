package com.displaytag.myDisplaytag.tableDecrator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.ObjectUtils;
import org.displaytag.decorator.TableDecorator;
import org.displaytag.model.TableModel;


  public class CheckboxTableDecorator extends TableDecorator
  {
  
      private String id = "id";
  
      private Map params;
  
      private List checkedIds;
  
      private String fieldName = "_chk";
  
     /**
       * Setter for <code>id</code>.
       * @param id The id to set.
       */
      public void setId(String id)
      {
         this.id = id;
         System.out.println("setItemNo");
     }
  
      /**
54       * Setter for <code>fieldName</code>.
55       * @param fieldName The fieldName to set.
56       */
      public void setFieldName(String fieldName)
      {
          this.fieldName = fieldName;
          System.out.println("setFieldName");
      }
  
      /**
63       * @see org.displaytag.decorator.Decorator#init(javax.servlet.jsp.PageContext, java.lang.Object,
64       * org.displaytag.model.TableModel)
65       */
      public void init(PageContext pageContext, Object decorated, TableModel tableModel)
      {
          super.init(pageContext, decorated, tableModel);
          String[] params = pageContext.getRequest().getParameterValues(fieldName);
          checkedIds = params != null ? new ArrayList(Arrays.asList(params)) : new ArrayList(0);
          System.out.println("init");
      }
  
      /**
74       * @see org.displaytag.decorator.TableDecorator#finish()
75       */
      public void finish()
      {
  
    	  System.out.println(checkedIds.isEmpty());
          if (!checkedIds.isEmpty())
          {
              JspWriter writer = getPageContext().getOut();
              for (Iterator it = checkedIds.iterator(); it.hasNext();)
              {
                  String name = (String) it.next();
                  StringBuffer buffer = new StringBuffer();
                  buffer.append("<input type=\"hidden\" name=\"");
                  buffer.append(fieldName);
                  buffer.append("\" value=\"");
                  buffer.append(name);
                  buffer.append("\">");
                  try
                  {
                      writer.write(buffer.toString());
                      System.out.println(buffer.toString());
                  }
                  catch (IOException e)
                  {
                      // should never happen
                  }
              }
         }
         System.out.println("finish"); 
         super.finish();
 
     }
 
     public String getCheckbox()
     {
 
         String evaluatedId = ObjectUtils.toString(evaluate(id));
 
         boolean checked = checkedIds.contains(evaluatedId);
 
         StringBuffer buffer = new StringBuffer();
         buffer.append("<input type=\"checkbox\" name=\"_chk\" value=\"");
         buffer.append(evaluatedId);
         buffer.append("\"");
         if (checked)
         {
             checkedIds.remove(evaluatedId);
             buffer.append(" checked=\"checked\"");
         }
         buffer.append("/>");
         System.out.println(buffer.toString());
         return buffer.toString();
         
     }
		public String addRowId()
		{
		return "myrow" + evaluate(id);
		}
 }

