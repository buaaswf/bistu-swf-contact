package cn.bistu.service.impl;

import cn.bistu.dao.ContactDao;
import cn.bistu.dao.impl.ContactDaoMySQLImpl;
import cn.bistu.entity.Contact;
import cn.bistu.exception.NameRepeatException;
import cn.bistu.service.ContactService;

import java.util.List;

/**
 * 业务逻辑层
 * 处理项目中出现的业务逻辑
 */
public class ContactServiceImpl implements ContactService{

    ContactDao dao = new ContactDaoMySQLImpl();

    @Override
    public void addContact(Contact contact) throws NameRepeatException {
        //执行业务逻辑判断
        if (dao.checkContact(contact.getName())){
            /**
             * 注意：如果业务层方法出现业务异常，则返回标记(自定义异常)到servlet
             */
            throw new NameRepeatException("姓名重复，不可使用");
        }
        dao.addContact(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }

    @Override
    public void deleteContact(String id) {
        dao.deleteContact(id);
    }

    @Override
    public List<Contact> finaAll() {
        return dao.findAll();
    }

    @Override
    public Contact findById(String id) {
        return dao.findById(id);
    }
}
