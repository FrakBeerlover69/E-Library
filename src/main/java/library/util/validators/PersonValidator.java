package library.util.validators;

import library.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        try{
            Integer.parseInt(person.getYearOfBirth());
        }catch(NumberFormatException e){
            errors.rejectValue("yearOfBirth","", "Enter numerical date of birth");
        }
    }
}
