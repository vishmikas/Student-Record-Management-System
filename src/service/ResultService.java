package service;

import dao.ResultDAO;
import model.Result;

public class ResultService {

    public static boolean addResult(Result result) {

        if (result.getStudentId() <= 0) {
            System.out.println("Invalid Student ID");
            return false;
        }
        if (result.getSubject() == null || result.getSubject().isEmpty()) {
            System.out.println("Subject cannot be empty");
            return false;
        }
        if (result.getMarks() <= 0 || result.getMarks() > 100) {
            System.out.println("Marks must be between 0 and 100");
            return false;
        }

        return ResultDAO.addResult(result);
    }
}
