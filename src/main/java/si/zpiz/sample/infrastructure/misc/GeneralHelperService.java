package si.zpiz.sample.infrastructure.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralHelperService {
    @Autowired
    @SuppressWarnings("unused")
    private EnvironmentPropertiesPrinter environmentPropertiesPrinter;
}
