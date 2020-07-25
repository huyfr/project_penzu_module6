package pendzu.sduteam.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import pendzu.sduteam.models.Role;
import pendzu.sduteam.services.impl.RoleServiceImpl;


import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class RoleFormatter implements Formatter<Role> {
    RoleServiceImpl roleService;
    @Autowired
    public RoleFormatter (RoleServiceImpl roleService){
        this.roleService = roleService;
    }
    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        Optional<Role> optionalRole= roleService.findById(Long.parseLong(text));
        Role role = optionalRole.get();
        return role;
    }

    @Override
    public String print(Role object, Locale locale) {
        return object.toString();
    }
}
