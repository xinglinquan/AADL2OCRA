package aadl2ocra.template;

import aadl2ocra.template.ConnectionTemplate;
import aadl2ocra.template.FeatureTemplate;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.ThreadImplementation;
import org.osate.aadl2.impl.StringLiteralImpl;

@SuppressWarnings("all")
public class ThreadTemplate {
  public static CharSequence genThread(final ThreadImplementation thread) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("COMPONENT ");
    String _name = thread.getName();
    _builder.append(_name);
    _builder.newLineIfNotEmpty();
    {
      int _size = thread.getAllFeatures().size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("INTERFACE");
        _builder.newLine();
        CharSequence _genFeature = FeatureTemplate.genFeature(thread.getAllFeatures());
        _builder.append(_genFeature);
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _dealContract = ThreadTemplate.dealContract(thread);
    _builder.append(_dealContract);
    _builder.append("\t\t");
    _builder.newLineIfNotEmpty();
    {
      int _size_1 = thread.getAllSubcomponents().size();
      boolean _greaterThan_1 = (_size_1 > 0);
      if (_greaterThan_1) {
        _builder.append("REFINEMENT");
        _builder.newLine();
        {
          EList<Subcomponent> _allSubcomponents = thread.getAllSubcomponents();
          for(final Subcomponent component : _allSubcomponents) {
            _builder.append("SUB ");
            String _name_1 = component.getName();
            _builder.append(_name_1);
            _builder.append(" : ");
            String _name_2 = component.getComponentImplementation().getName();
            _builder.append(_name_2);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      int _size_2 = thread.getAllConnections().size();
      boolean _greaterThan_2 = (_size_2 > 0);
      if (_greaterThan_2) {
        CharSequence _genConnection = ConnectionTemplate.genConnection(thread.getAllConnections());
        _builder.append(_genConnection);
        _builder.append("    ");
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _dealRefinedBy = ThreadTemplate.dealRefinedBy(thread);
    _builder.append(_dealRefinedBy);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence dealContract(final ThreadImplementation thread) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<PropertyAssociation> _allPropertyAssociations = thread.getAllPropertyAssociations();
      boolean _tripleNotEquals = (_allPropertyAssociations != null);
      if (_tripleNotEquals) {
        {
          EList<PropertyAssociation> _allPropertyAssociations_1 = thread.getAllPropertyAssociations();
          for(final PropertyAssociation pa : _allPropertyAssociations_1) {
            {
              if ((pa.getProperty().getNamespace().getName().equals("OCRA") && pa.getProperty().getName().equals("Contract"))) {
                {
                  EList<ModalPropertyValue> _ownedValues = pa.getOwnedValues();
                  for(final ModalPropertyValue value : _ownedValues) {
                    PropertyExpression ownedValue = value.getOwnedValue();
                    _builder.newLineIfNotEmpty();
                    String _switchResult = null;
                    boolean _matched = false;
                    if (ownedValue instanceof StringLiteralImpl) {
                      _matched=true;
                      _switchResult = ((StringLiteralImpl)ownedValue).getValue();
                    }
                    if (!_matched) {
                      _switchResult = ownedValue.toString();
                    }
                    _builder.append(_switchResult);
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public static CharSequence dealRefinedBy(final ThreadImplementation thread) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<PropertyAssociation> _allPropertyAssociations = thread.getAllPropertyAssociations();
      boolean _tripleNotEquals = (_allPropertyAssociations != null);
      if (_tripleNotEquals) {
        {
          EList<PropertyAssociation> _allPropertyAssociations_1 = thread.getAllPropertyAssociations();
          for(final PropertyAssociation pa : _allPropertyAssociations_1) {
            {
              if ((pa.getProperty().getNamespace().getName().equals("OCRA") && pa.getProperty().getName().equals("RefinedBy"))) {
                {
                  EList<ModalPropertyValue> _ownedValues = pa.getOwnedValues();
                  for(final ModalPropertyValue value : _ownedValues) {
                    PropertyExpression ownedValue = value.getOwnedValue();
                    _builder.newLineIfNotEmpty();
                    String _switchResult = null;
                    boolean _matched = false;
                    if (ownedValue instanceof StringLiteralImpl) {
                      _matched=true;
                      _switchResult = ((StringLiteralImpl)ownedValue).getValue();
                    }
                    if (!_matched) {
                      _switchResult = ownedValue.toString();
                    }
                    _builder.append(_switchResult);
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
}
