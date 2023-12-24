package com.Microservices.Employee.Services.EmployeeImpl;

import com.Microservices.Employee.Dto.ApiResponseDto;
import com.Microservices.Employee.Dto.DepartmentDto;
import com.Microservices.Employee.Dto.EmployeeDto;
import com.Microservices.Employee.Dto.OrganisationDto;
import com.Microservices.Employee.Entity.Employee;
import com.Microservices.Employee.Repository.EmployeeRepo;
import com.Microservices.Employee.Services.EmployeeServices;
import com.Microservices.Employee.Services.FeignClientApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeServices {
    @Autowired
    private Employee employee;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper mapper;
//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private WebClient webClient;
    @Autowired
    private FeignClientApi feignClientApi;
    @Autowired
    private ApiResponseDto apiResponse;

    @Override
    public EmployeeDto newEmployee(EmployeeDto employeeDto) {
        employee = mapper.map(employeeDto, Employee.class);
        employee.setFullName(employeeDto.getFirstName() + " " + employeeDto.getLastName());
        Employee newEmployee = employeeRepo.save(employee);
        return mapper.map(newEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long empId) {
        employee = employeeRepo.getReferenceById(empId);
        if (employee != null) {
            employee.setContactNo(employeeDto.getContactNo());
            employee.setEmailId(employeeDto.getEmailId());
            employee.setEmpCode(employeeDto.getEmpCode());
            employee.setFullName(employeeDto.getFullName());
            employee.setLastName(employeeDto.getLastName());
            employee.setOrgId(employeeDto.getOrgId());
            employee.setFullName(employeeDto.getFirstName() + " " + employeeDto.getLastName());
            Employee newEmployee = employeeRepo.save(employee);
            return mapper.map(newEmployee, EmployeeDto.class);
        }
        return null;
    }

    @Override
    public List<EmployeeDto> empByEmail(String emailId) {
        if (!emailId.isEmpty()) {
            List<Employee> employee = employeeRepo.findByEmailId(emailId);
            return employee.stream().map(emp -> mapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<EmployeeDto> empByFirstName(String firstName) {
        if (!firstName.isEmpty()) {
            List<Employee> employee = employeeRepo.findByFirstName(firstName);
            return employee.stream().map(emp -> mapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<EmployeeDto> empByLastName(String lastName) {
        if (!lastName.isEmpty()) {
            List<Employee> employee = employeeRepo.findByLastName(lastName);
            return employee.stream().map(emp -> mapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<EmployeeDto> empByName(String name) {
        if (!name.isEmpty()) {
            List<Employee> employee = employeeRepo.findByFirstName(name);
            return employee.stream().map(emp -> mapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
        }
        return null;
    }


    @Retry(name = "${spring.application.name}",fallbackMethod = "empFallbackMethod")
//    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "empFallbackMethod")
    @Override
    public ApiResponseDto empById(Long empId) {
        Employee emp = employeeRepo.getReferenceById(empId);
        if (emp != null) {
//            ---------------------------RestTemplate----------------------------------
//            ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//                    "http://localhost:8080/departments/deptById/"+emp.getDeptId(),
//                    DepartmentDto.class);
//            DepartmentDto departmentDto = responseEntity.getBody();
//            ---------------------------WebClient------------------------------
            DepartmentDto departmentDto = webClient.get()
                    .uri("http://localhost:8080/departments/deptById/" + emp.getDeptId())
                    .retrieve()
                    .bodyToMono(DepartmentDto.class).block();
            OrganisationDto organisationDto = webClient.get()
                    .uri("http://localhost:8083/organisation/orgById/"+ emp.getOrgId())
                    .retrieve().bodyToMono(OrganisationDto.class).block();
//            -----------------------------OpenFeign Client-----------------------------------
//            DepartmentDto departmentDto = feignClientApi.deptById(emp.getDeptId());
            EmployeeDto employeeDto = mapper.map(emp, EmployeeDto.class);
            apiResponse.setEmployeeDto(employeeDto);
            apiResponse.setDepartmentDto(departmentDto);
            apiResponse.setOrganisationDto(organisationDto);
            return apiResponse;
        }
        return null;
    }

    @Override
    public List<EmployeeDto> empByEmpCode(String empCode) {
        if (!empCode.isEmpty()) {
            List<Employee> employee = employeeRepo.findByEmpCode(empCode);
            return employee.stream().map(emp -> mapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Void deleteById(Long empId) {
        if (empId != null && empId > 0) employeeRepo.deleteById(empId);
        return null;
    }

    @Override
    public List<EmployeeDto> findAllEmp() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map(emp -> mapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
    }

    public ApiResponseDto empFallbackMethod(Long empId,Exception exception){
        Employee emp = employeeRepo.getReferenceById(empId);
        if (emp != null) {
            DepartmentDto departmentDto = new DepartmentDto();// Default value is passed in departmentDto
            EmployeeDto employeeDto = mapper.map(emp, EmployeeDto.class);
            apiResponse.setEmployeeDto(employeeDto);
            apiResponse.setDepartmentDto(departmentDto);
            OrganisationDto organisationDto = new OrganisationDto();
            apiResponse.setOrganisationDto(organisationDto);
            return apiResponse;
        }
        return null;
    }
}
