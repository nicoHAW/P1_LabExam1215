package _untouchable_.supportC2x00;


import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleDescriptor.Version;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;


public class EnvironmentAnalyzer {
    
    public static int getAvailableCores() {
        return Runtime.getRuntime().availableProcessors();
    }//method()
    
    public static String getJavaVersion(){
        return System.getProperty( "java.version" );
    }// method()
    
    public static String getJUnitJupiterVersion(){
        final Class<Test> testClass = org.junit.jupiter.api.Test.class;
        final Module module = testClass.getModule();
        if( null != module ){
            final ModuleDescriptor moduleDescriptor = module.getDescriptor();
            if( null != moduleDescriptor ){
                final Optional<Version> optionalVersion = moduleDescriptor.version();
                if( optionalVersion.isPresent() ){
                    return optionalVersion.get().toString();
                }//if
            }//if
        }//if
        final Package pakage = testClass.getPackage();
        if( null != pakage ){
            final String version = pakage.getImplementationVersion();
            if( null != version ){
                return version;
            }//if
        }//if
        return "???";
    }//method()
    //
    public static String getJUnitPlatformVersion(){
        final Class<JUnitPlatform> jUnitPlatformClass = org.junit.platform.runner.JUnitPlatform.class;
        final Module module = jUnitPlatformClass.getModule();
        if( null != module ){
            final ModuleDescriptor moduleDescriptor = module.getDescriptor();
            if( null != moduleDescriptor ){
                final Optional<Version> optionalVersion = moduleDescriptor.version();
                if( optionalVersion.isPresent() ){
                    return optionalVersion.get().toString();
                }//if
            }//if
        }//if
        final Package pakage = jUnitPlatformClass.getPackage();
        if( null != pakage ){
            final String version = pakage.getImplementationVersion();
            if( null != version ){
                return version;
            }//if
        }//if
        return "???";
    }//method()
    
}//class
//see: https://stackoverflow.com/questions/59377304/accessing-junit-version-during-runtime
