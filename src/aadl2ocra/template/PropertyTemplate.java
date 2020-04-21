package aadl2ocra.template;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.impl.DataTypeImpl;
import org.osate.aadl2.impl.ListValueImpl;
import org.osate.aadl2.impl.NamedValueImpl;
import org.osate.aadl2.impl.StringLiteralImpl;

@SuppressWarnings("all")
public class PropertyTemplate {
  public static CharSequence dealProperty(final PropertyAssociation property) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModalPropertyValue> _ownedValues = property.getOwnedValues();
      for(final ModalPropertyValue value : _ownedValues) {
        PropertyExpression ownedValue = value.getOwnedValue();
        _builder.newLineIfNotEmpty();
        CharSequence _switchResult = null;
        boolean _matched = false;
        if (ownedValue instanceof NamedValueImpl) {
          _matched=true;
          _switchResult = PropertyTemplate.parseNamedValue(((NamedValueImpl)ownedValue).getNamedValue());
        }
        if (!_matched) {
          if (ownedValue instanceof ListValueImpl) {
            _matched=true;
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append("{");
            String _parseListValue = PropertyTemplate.parseListValue(((ListValueImpl)ownedValue));
            _builder_1.append(_parseListValue);
            _builder_1.append("};");
            _builder_1.newLineIfNotEmpty();
            _switchResult = _builder_1;
          }
        }
        if (!_matched) {
          _switchResult = ownedValue.toString();
        }
        _builder.append(_switchResult);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public static String parseNamedValue(final AbstractNamedValue value) {
    boolean _matched = false;
    if (value instanceof EnumerationLiteral) {
      _matched=true;
      boolean _equals = ((EnumerationLiteral)value).getName().toString().equals("Boolean");
      if (_equals) {
        return "boolean;";
      } else {
        boolean _equals_1 = ((EnumerationLiteral)value).getName().toString().equals("Float");
        if (_equals_1) {
          return "real;";
        } else {
          boolean _equals_2 = ((EnumerationLiteral)value).getName().toString().equals("Integer");
          if (_equals_2) {
            return "integer;";
          }
        }
      }
    }
    if (!_matched) {
      boolean _equals = value.getClass().getName().toString().equals("Boolean");
      if (_equals) {
        return "boolean;";
      } else {
        boolean _equals_1 = value.getClass().getName().toString().equals("Float");
        if (_equals_1) {
          return "real;";
        } else {
          boolean _equals_2 = value.getClass().getName().toString().equals("Integer");
          if (_equals_2) {
            return "integer;";
          }
        }
      }
    }
    return null;
  }
  
  public static String parseListValue(final ListValueImpl value) {
    String temp = "";
    for (int i = 0; (i < value.getOwnedListElements().size()); i++) {
      {
        PropertyExpression expression = value.getOwnedListElements().get(i);
        boolean _matched = false;
        if (expression instanceof StringLiteralImpl) {
          _matched=true;
          int _size = value.getOwnedListElements().size();
          int _minus = (_size - 1);
          boolean _notEquals = (i != _minus);
          if (_notEquals) {
            System.out.println(i);
            String _temp = temp;
            String _value = ((StringLiteralImpl)expression).getValue();
            String _plus = (_value + ",");
            temp = (_temp + _plus);
          } else {
            String _temp_1 = temp;
            String _value_1 = ((StringLiteralImpl)expression).getValue();
            temp = (_temp_1 + _value_1);
          }
        }
        if (!_matched) {
          String _temp = temp;
          String _string = expression.toString();
          temp = (_temp + _string);
        }
      }
    }
    return temp;
  }
  
  public static String parseClassifier(final Classifier classifier) {
    Classifier clazz = classifier;
    try {
      String type = ((DataTypeImpl) clazz).getName();
      return type;
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception exception = (Exception)_t;
        return "Classifier can\'t be cast to DataTypeImpl . fdafds in PropertyTemplate.xtend !! \n";
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
