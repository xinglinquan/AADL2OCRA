package aadl2ocra.template;

import aadl2ocra.template.ConnectionTemplate;
import aadl2ocra.template.FeatureTemplate;
import aadl2ocra.template.ThreadTemplate;
import aadl2ocra.utils.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.ProcessImplementation;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SubcomponentType;
import org.osate.aadl2.ThreadImplementation;
import org.osate.aadl2.ThreadSubcomponent;
import org.osate.aadl2.impl.StringLiteralImpl;

@SuppressWarnings("all")
public class ProcessTemplate {
  public static CharSequence genProcess(final ProcessImplementation process) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("COMPONENT ");
    String _convertPoint = StringUtils.convertPoint(process.getName());
    _builder.append(_convertPoint);
    _builder.newLineIfNotEmpty();
    {
      int _size = process.getAllFeatures().size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("INTERFACE");
        _builder.newLine();
        CharSequence _genFeature = FeatureTemplate.genFeature(process.getAllFeatures());
        _builder.append(_genFeature);
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _dealContract = ProcessTemplate.dealContract(process);
    _builder.append(_dealContract);
    _builder.append("\t\t");
    _builder.newLineIfNotEmpty();
    {
      int _size_1 = process.getAllSubcomponents().size();
      boolean _greaterThan_1 = (_size_1 > 0);
      if (_greaterThan_1) {
        _builder.append("REFINEMENT");
        _builder.newLine();
        {
          EList<Subcomponent> _allSubcomponents = process.getAllSubcomponents();
          for(final Subcomponent component : _allSubcomponents) {
            _builder.append("SUB ");
            String _name = component.getName();
            _builder.append(_name);
            _builder.append(" : ");
            String _convertPoint_1 = StringUtils.convertPoint(component.getComponentImplementation().getName());
            _builder.append(_convertPoint_1);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      int _size_2 = process.getAllConnections().size();
      boolean _greaterThan_2 = (_size_2 > 0);
      if (_greaterThan_2) {
        CharSequence _genConnection = ConnectionTemplate.genConnection(process.getAllConnections());
        _builder.append(_genConnection);
        _builder.append("    ");
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _dealRefinedBy = ProcessTemplate.dealRefinedBy(process);
    _builder.append(_dealRefinedBy);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      int _size_3 = process.getOwnedThreadSubcomponents().size();
      boolean _greaterThan_3 = (_size_3 > 0);
      if (_greaterThan_3) {
        {
          EList<ThreadSubcomponent> _ownedThreadSubcomponents = process.getOwnedThreadSubcomponents();
          for(final ThreadSubcomponent threadsubcomponent : _ownedThreadSubcomponents) {
            SubcomponentType _subcomponentType = threadsubcomponent.getSubcomponentType();
            ThreadImplementation threadImplementation = ((ThreadImplementation) _subcomponentType);
            _builder.newLineIfNotEmpty();
            CharSequence _genThread = ThreadTemplate.genThread(threadImplementation);
            _builder.append(_genThread);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public static CharSequence dealContract(final ProcessImplementation process) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<PropertyAssociation> _allPropertyAssociations = process.getAllPropertyAssociations();
      boolean _tripleNotEquals = (_allPropertyAssociations != null);
      if (_tripleNotEquals) {
        {
          EList<PropertyAssociation> _allPropertyAssociations_1 = process.getAllPropertyAssociations();
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
  
  public static CharSequence dealRefinedBy(final ProcessImplementation process) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<PropertyAssociation> _allPropertyAssociations = process.getAllPropertyAssociations();
      boolean _tripleNotEquals = (_allPropertyAssociations != null);
      if (_tripleNotEquals) {
        {
          EList<PropertyAssociation> _allPropertyAssociations_1 = process.getAllPropertyAssociations();
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
