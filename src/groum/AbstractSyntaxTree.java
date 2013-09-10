package groum;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class AbstractSyntaxTree {
	private CompilationUnit ast;
	
	protected AbstractSyntaxTree(String filename) {
		File file = new File(filename);
		try {
			parseFile(file);
		} catch (IOException e) {
			System.out.printf("File \"%s\" doesn't exist or cannot be read!\n", filename);
		}
	}
	
	protected void parseFile(File file) throws IOException{
		String source = FileUtils.readFileToString(file);
	    this.parseSource(source);
	}
	
	protected void parseSource(String source){
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(source.toCharArray());
		// In order to parse 1.5 code, some compiler options need to be set to 1.5
		@SuppressWarnings("rawtypes")
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_5, options);
		parser.setCompilerOptions(options);
		ast = (CompilationUnit) parser.createAST(null);
	}
	
	protected CompilationUnit getAST(){
		return ast;
	}
	
	protected ASTNode getRoot(){
		return ast.getRoot();
	}
	
	protected static String readableNodeType(ASTNode ast){
		switch (ast.getNodeType()) {
		case ASTNode.ANNOTATION_TYPE_DECLARATION: return "ANNOTATION_TYPE_DECLARATION";
		case ASTNode.ANNOTATION_TYPE_MEMBER_DECLARATION: return "ANNOTATION_TYPE_MUMBER_DECLARATION";
		case ASTNode.ANONYMOUS_CLASS_DECLARATION: return "ANONYMOUS_CLASS_DECLARATION | MALFORMED";
		case ASTNode.ARRAY_ACCESS: return "ARRAY_ACCESS | ORIGINAL";
		case ASTNode.ARRAY_CREATION: return "ARRAY_CREATION";
		case ASTNode.ARRAY_INITIALIZER: return "ARRAY_INITIALIZER | PROTECTED";
		case ASTNode.ARRAY_TYPE: return "ARRAY_TYPE";
		case ASTNode.ASSERT_STATEMENT: return "ASSERT_STATEMENT";
		case ASTNode.ASSIGNMENT: return "ASSIGNMENT";
		case ASTNode.BLOCK: return "BLOCK | RECOVERED";
		case ASTNode.BLOCK_COMMENT: return "BLOCK_COMMENT";
		case ASTNode.BOOLEAN_LITERAL: return "BOOLEAN_LITERAL";
		case ASTNode.BREAK_STATEMENT: return "BREAK_STATEMENT";
		case ASTNode.CAST_EXPRESSION: return "CAST_EXPRESSION";
		case ASTNode.CATCH_CLAUSE: return "CATCH_CLAUSE";
		case ASTNode.CHARACTER_LITERAL: return "CHARACTER_LITERAL";
		case ASTNode.CLASS_INSTANCE_CREATION: return "CLASS_INSTANCE_CREATION";
		case ASTNode.COMPILATION_UNIT: return "COMPILATION_UNIT";
		case ASTNode.CONDITIONAL_EXPRESSION: return "CONDITIONAL_EXPRESSION";
		case ASTNode.CONSTRUCTOR_INVOCATION: return "CONSTRUCTOR_INVOCATION";
		case ASTNode.CONTINUE_STATEMENT: return "CONTINUE_STATEMENT";
		case ASTNode.DO_STATEMENT: return "DO_STATEMENT";
		case ASTNode.EMPTY_STATEMENT: return "EMPTY_STATEMENT";
		case ASTNode.ENHANCED_FOR_STATEMENT: return "ENHANCED_FOR_STATEMENT";
		case ASTNode.ENUM_CONSTANT_DECLARATION: return "ENUM_CONSTANT_DECLARATION";
		case ASTNode.ENUM_DECLARATION: return "ENUM_DECLARATION";
		case ASTNode.EXPRESSION_STATEMENT: return "EXPRESSION_STATEMENT";
		case ASTNode.FIELD_ACCESS: return "FIELD_ACCESS";
		case ASTNode.FIELD_DECLARATION: return "FIELD_DECLARATION";
		case ASTNode.FOR_STATEMENT: return "FOR_STATEMENT";
		case ASTNode.IF_STATEMENT: return "IF_STATEMENT";
		case ASTNode.IMPORT_DECLARATION: return "IMPORT_DECLARATION";
		case ASTNode.INFIX_EXPRESSION: return "INFIX_EXPRESSION";
		case ASTNode.INITIALIZER: return "INITIALIZER";
		case ASTNode.INSTANCEOF_EXPRESSION: return "INSTANCEOF_EXPRESSION";
		case ASTNode.JAVADOC: return "JAVADOC";
		case ASTNode.LABELED_STATEMENT: return "LABELED_STATEMENT";
		case ASTNode.LINE_COMMENT: return "LINE_COMMENT";
		case ASTNode.MARKER_ANNOTATION: return "MARKER_ANNOTATION";
		case ASTNode.MEMBER_REF: return "MEMBER_REF";
		case ASTNode.MEMBER_VALUE_PAIR: return "MEMBER_VALUE_PAIR";
		case ASTNode.METHOD_DECLARATION: return "METHOD_DECLARATION";
		case ASTNode.METHOD_INVOCATION: return "METHOD_INVOCATION";
		case ASTNode.METHOD_REF: return "METHOD_REF";
		case ASTNode.METHOD_REF_PARAMETER: return "METHOD_REF_PARAMETER";
		case ASTNode.MODIFIER: return "MODIFIER";
		case ASTNode.NORMAL_ANNOTATION: return "NORMAL_ANNOTATION";
		case ASTNode.NULL_LITERAL: return "NULL_LITERAL";
		case ASTNode.NUMBER_LITERAL: return "NUMBER_LIERAL";
		case ASTNode.PACKAGE_DECLARATION: return "PACKAGE_DECLARATION";
		case ASTNode.PARAMETERIZED_TYPE: return "PARAMETERIZED_TYPE";
		case ASTNode.PARENTHESIZED_EXPRESSION: return "PARENTHESIZED_EXPRESSION";
		case ASTNode.POSTFIX_EXPRESSION: return "POSTFIX_EXPRESSION";
		case ASTNode.PREFIX_EXPRESSION: return "PREFIX_EXPRESSION";
		case ASTNode.PRIMITIVE_TYPE: return "PRIMITIVE_TYPE";
		case ASTNode.QUALIFIED_NAME: return "QUALIFIED_NAME";
		case ASTNode.QUALIFIED_TYPE: return "QUALIFIED_TYPE";
		case ASTNode.RETURN_STATEMENT: return "RETURN_STATEMENT";
		case ASTNode.SIMPLE_NAME: return "SIMPLE_NAME";
		case ASTNode.SIMPLE_TYPE: return "SIMPLE_TYPE";
		case ASTNode.SINGLE_MEMBER_ANNOTATION: return "SINGLE_MEMBER_ANNOTATION";
		case ASTNode.SINGLE_VARIABLE_DECLARATION: return "SINGLE_VARIABLE_DECLARATION";
		case ASTNode.STRING_LITERAL: return "STRING_LITERAL";
		case ASTNode.SUPER_CONSTRUCTOR_INVOCATION: return "SUPER_CONSTRUCTOR_INVOCATION";
		case ASTNode.SUPER_FIELD_ACCESS: return "SUPER_FIELD_ACCESS";
		case ASTNode.SUPER_METHOD_INVOCATION: return "SUPER_METHOD_INVOCATION";
		case ASTNode.SWITCH_CASE: return "SWITH_CASE";
		case ASTNode.SWITCH_STATEMENT: return "SWITH_STATEMENT";
		case ASTNode.SYNCHRONIZED_STATEMENT: return "SYNCRONIZED_STATEMENT";
		case ASTNode.TAG_ELEMENT: return "TAG_ELEMENT";
		case ASTNode.TEXT_ELEMENT: return "TEXT_ELEMENT";
		case ASTNode.THIS_EXPRESSION: return "THIS_EXPRESSION";
		case ASTNode.THROW_STATEMENT: return "THROW_STATEMENT";
		case ASTNode.TRY_STATEMENT: return "TRY_STATEMENT";
		case ASTNode.TYPE_DECLARATION: return "TYPE_DECLARATION";
		case ASTNode.TYPE_DECLARATION_STATEMENT: return "TYPE_DECLARATION_STATEMENT";
		case ASTNode.TYPE_LITERAL: return "TYPE_LITERAL";
		case ASTNode.TYPE_PARAMETER: return "TYPE_PARAMETER";
		case ASTNode.UNION_TYPE: return "UNION_TYPE";
		case ASTNode.VARIABLE_DECLARATION_EXPRESSION: return "VARIABLE_DECLARATION_EXPRESSION";
		case ASTNode.VARIABLE_DECLARATION_FRAGMENT: return "VARIABLE_DECLARATION_FRAGMENT";
		case ASTNode.VARIABLE_DECLARATION_STATEMENT: return "VARIABLE_DECLARATION_STATEMENT";
		default: return "#WrongType";
		}
	}
	
}
