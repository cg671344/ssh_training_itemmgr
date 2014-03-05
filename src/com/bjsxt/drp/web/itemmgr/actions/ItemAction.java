package com.bjsxt.drp.web.itemmgr.actions;

import org.displaytag.tags.*;
import org.displaytag.util.ParamEncoder;

import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.ui.ModelMap;

import com.bjsxt.drp.business.itemmgr.manager.ItemManager;
import com.bjsxt.drp.business.itemmgr.model.Item;
import com.bjsxt.drp.business.itemmgr.model.ItemCategory;
import com.bjsxt.drp.business.itemmgr.model.ItemUnit;
import com.bjsxt.drp.business.util.PageModel;
import com.bjsxt.drp.web.itemmgr.forms.ItemActionForm;

/**
 * ͳһ�������е�����
 * @author Administrator
 *
 */
public class ItemAction extends BaseAction {

	private ItemManager itemManager;
	
	private String uploadPath;
	
	/**
	 * ���û�д����κα�ʶ��������command����������Ĭ�ϵ���unspecified����
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("ItemAction=>>unspecified()");
		ActionForward listActionForward = new ActionForward("/index.jsp", true);
		return listActionForward;
	}

	/**
	 * �������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		ItemActionForm iaf = (ItemActionForm)form;
		
		//����Itemʵ����󣬲���ActionForm�е��������õ�Item������
		Item item = new Item();
		
		//������ֵ��ItemActionForm���󿽱���Item����
		BeanUtils.copyProperties(item, iaf);
		
		//����ItemCategory
		ItemCategory ic = new ItemCategory();
		ic.setId(iaf.getCategoryId());
		item.setCategory(ic);
		
		//����itemUnit
		ItemUnit iu = new ItemUnit();
		iu.setId(iaf.getUnitId());
		item.setUnit(iu);
		
		//����ҵ���߼�����
		itemManager.addItem(item);
		ActionForward af = new ActionForward("item.do?command=list&pageNo=" + 
				                              iaf.getPageNo() + 
				                              "&pageSize=" + iaf.getPageSize(), true);
		return af;
	}

	/**
	 * ɾ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		ItemActionForm iaf = (ItemActionForm)form;
		
		//����ҵ���߼�����
		itemManager.deleteItem(iaf.getSelectFlag());
		ActionForward af = new ActionForward("item.do?command=list&pageNo=" + 
                iaf.getPageNo() + 
                "&pageSize=" + iaf.getPageSize(), true);
		return af;
	}

	/**
	 * �������ϴ����ѯ��Ҫ�޸ĵ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modifyDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		ItemActionForm iaf = (ItemActionForm)form;
		
		//����ҵ���߼�����
		Item item = itemManager.findItemById(iaf.getItemNo());
		
		//����ѯ����ŵ�request��
		request.setAttribute("item", item);
		return mapping.findForward("modify_detail");
	}

	/**
	 * �޸�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		ItemActionForm iaf = (ItemActionForm)form;
		
		//����Itemʵ����󣬲���ActionForm�е��������õ�Item������
		Item item = new Item();
		
		//������ֵ��ItemActionForm���󿽱���Item����
		BeanUtils.copyProperties(item, iaf);
				
		//����ItemCategory
		ItemCategory ic = new ItemCategory();
		ic.setId(iaf.getCategoryId());
		item.setCategory(ic);
		
		//����itemUnit
		ItemUnit iu = new ItemUnit();
		iu.setId(iaf.getUnitId());
		item.setUnit(iu);
		
		//����ҵ���߼�����
		itemManager.modifyItem(item);
		ActionForward af = new ActionForward("item.do?command=list&pageNo=" + 
                iaf.getPageNo() + 
                "&pageSize=" + iaf.getPageSize(), true);
		return af;
	}

	/**
	 * �������ϴ����ѯ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		ItemActionForm iaf = (ItemActionForm)form;
		
		//����ҵ���߼�����
		Item item = itemManager.findItemById(iaf.getItemNo());
		
		//����ѯ����ŵ�request��
		request.setAttribute("item", item);

		return mapping.findForward("find_detail");
	}

	/**
	 * ��ѯȫ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		ItemActionForm iaf = (ItemActionForm)form;
		
		//����ҵ���߼�����
		PageModel pageModel = itemManager.findAllItem(iaf.getPageNo(), iaf.getPageSize(), iaf.getClientIdOrName());
	
		//����ѯ����ŵ�request��
		request.setAttribute("pagemodel", pageModel);
	
		return mapping.findForward("list_success");
	}
	public static  String getPageParamName(String id){  
	   return  new  org.displaytag.util.ParamEncoder(id). 
	   		        encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);  
	}

	public ActionForward listDisplaytag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageIndex;
		String name = (new ParamEncoder("element").
				 encodeParameterName(TableTagParameters.PARAMETER_PAGE));
		//
		Map data = request.getParameterMap();
		String pageIndexString = request.getParameter(name);
		
		//System.out.println("pageIndexString:"+pageIndexString);
		if(pageIndexString==null||pageIndexString=="")
			pageIndex=1;
		else
			pageIndex = Integer.parseInt(pageIndexString);
		PageModel pageModel = itemManager.findAllItem(pageIndex,2, null);
		List result= pageModel.getList();
		int total=pageModel.getTotalRecords();
		Map<String,Object>rs=new HashMap<String,Object>();
		rs.put("results", result);
		rs.put("total",total);
		rs.put("pagesize",2);
		request.setAttribute("rs", rs);
		return mapping.findForward("list_displaytag");
	}
	
	/**
	 * uploadҳ����ϸ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//��ȡ��ҳ������ύ������ֵ
		ItemActionForm iaf = (ItemActionForm)form;
		
		//����ҵ���߼�����
		Item item = itemManager.findItemById(iaf.getItemNo());
		
		//����ѯ����ŵ�request��
		request.setAttribute("item", item);
		
		return mapping.findForward("upload_detail");
	}

	/**
	 * �ϴ�ͼƬ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		ItemActionForm iaf = (ItemActionForm)form;
		FormFile myFile = iaf.getFileName();
		if(myFile != null){
			//���ϴ����ļ������浽��images/item��
			String realPath = request.getSession().getServletContext().getRealPath("/images/item/");
			System.out.println("realpath" + realPath);
			//FileOutputStream fos = new FileOutputStream("C:\\apache-tomcat-5.5.17\\webapps\\struts_training_itemmgr\\images\\item\\"+iaf.getItemNo() + ".gif");
			//System.out.println(request.getContextPath());
			String path=realPath +"/"+ iaf.getItemNo() + ".gif";
			System.out.println(path);
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(myFile.getFileData());
			fos.flush();
			fos.close();
		}
		ActionForward af = new ActionForward("item.do?command=list&pageNo=" + 
               iaf.getPageNo() + 
               "&pageSize=" + iaf.getPageSize(), true);
		return af;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

}