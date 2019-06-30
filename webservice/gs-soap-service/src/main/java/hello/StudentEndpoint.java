package hello;

import com.mukul.test.soap_service.Course;
import com.mukul.test.soap_service.GetStudentDetailRequest;
import com.mukul.test.soap_service.GetStudentDetailResponse;
import com.mukul.test.soap_service.Student;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {

    private static final String STUDENT_NAMESPACE = "http://mukul.com/test/soap-service";

    @PayloadRoot(namespace = STUDENT_NAMESPACE, localPart = "getStudentDetailRequest")
    @ResponsePayload
    public GetStudentDetailResponse getStudentDetailDescription(@RequestPayload GetStudentDetailRequest request) {
        System.out.println(request.getName());

        GetStudentDetailResponse response = new GetStudentDetailResponse();
        Student student = new Student();
        student.setName(request.getName());
        student.setAge(20);
        student.setCourse(Course.MATHS);

        response.setStudent(student);

        return response;
    }
}
