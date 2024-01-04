package com.example.hotelsmanagementsystem.repository;

import com.example.hotelsmanagementsystem.models.Department;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector implements DatabaseInterface{
    @Override
    public List<String> getAllDepartments() {
//        Query<Department> query = session.createNativeQuery("CALL GetAllFoos()").addEntity(Department.class);
//        List<Department> allFoos = query.list();
//
//        List result = query.list();
//        for(int i=0; i<result.size(); i++){
//            Stock stock = (Stock)result.get(i);
//            System.out.println(stock.getStockCode());
//        }
//
//
//        try (Session session = sessionFactory.openSession()) {
//            session.getTransaction().begin();
//
//            ProcedureCall call = session.createStoredProcedureCall("get_employee_by_id");
//
//            ProcedureParameter<Long> in_parameter = call
//                    .registerParameter(1,Long.class, ParameterMode.IN);
//            call.setParameter(in_parameter, 1L);
//
//            call.registerParameter(2, String.class, ParameterMode.OUT);
//            call.registerParameter(3, String.class, ParameterMode.OUT);
//            call.registerParameter(4, String.class, ParameterMode.OUT);
//
//            ProcedureOutputs outputs = call.getOutputs();
//
//            int updateCount = ((UpdateCountOutput) outputs.getCurrent()).getUpdateCount();
//
//            Assertions.assertEquals(1, updateCount);
//
//            Assertions.assertEquals("NAME_1@email.com", outputs.getOutputParameterValue(2));
//            Assertions.assertEquals("FNAME_1", outputs.getOutputParameterValue(3));
//            Assertions.assertEquals("LNAME_1", outputs.getOutputParameterValue(4));
//
//            session.getTransaction().commit();
//        }catch (Exception e){
//
//        }
        return new ArrayList<>();
    }
}
