package _untouchable_.supportC2x00;
// TODO: code contains "fail" - NO JUnit access in support package


import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;


public class RefTypeInfoContainer {
    
    final Class<?> referenceTypeUnderTest;
    final String referenceTypeName; 
    final String pathToJavaRootBinHome;
    final String pathToJavaRootSrcHome;
    final File sourceCodeOfClassUnderText;
    
    
    
    public RefTypeInfoContainer(
        final String refTypeName,
        final String localPackagePath,
        final String pathToJavaRootBinHome
    ) throws TestSupportException {
        Class<?> referenceTypeUnderTest = null;
        final String refTypeWithPath =  localPackagePath + "." + refTypeName;
        try{
            referenceTypeUnderTest = Class.forName( refTypeWithPath );
        }catch( final ClassNotFoundException ex ){
            throw new TestSupportException( String.format( "can NOT find \"%s\" -> %s",  refTypeName, ex.getMessage() ), ex );
        }finally{
            this.referenceTypeName = refTypeName;
            this.referenceTypeUnderTest = referenceTypeUnderTest;
            this.pathToJavaRootBinHome = pathToJavaRootBinHome;
            this.pathToJavaRootSrcHome = pathToJavaRootBinHome.replaceFirst( "/bin/$", "/src/" );
            final String localPackagePathAdapted = localPackagePath.replaceAll( "\\.", "\\\\" );
            final String pathToSourceCodeOfClassUnderText = pathToJavaRootSrcHome + localPackagePathAdapted + "\\" + refTypeName + ".java";
            this.sourceCodeOfClassUnderText = new File( pathToSourceCodeOfClassUnderText );
        }//try
    }//constructor()
    
    
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>: %s %s]",
            RefTypeInfoContainer.class.getSimpleName(),
            referenceTypeUnderTest.getSimpleName(),
            pathToJavaRootSrcHome
        );
    }//method()
    
}//class
