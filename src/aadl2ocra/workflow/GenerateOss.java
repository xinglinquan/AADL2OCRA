package aadl2ocra.workflow;

import aadl2ocra.template.ConnectionTemplate;
import aadl2ocra.template.FeatureTemplate;
import aadl2ocra.template.ProcessTemplate;
import aadl2ocra.utils.FileUtils;
import aadl2ocra.utils.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.ProcessImplementation;
import org.osate.aadl2.ProcessSubcomponent;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SubcomponentType;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.SystemSubcomponent;
import org.osate.aadl2.impl.StringLiteralImpl;

@SuppressWarnings("all")
public class GenerateOss {
  public static void generate(final SystemImplementation system, final String name) {
    FileUtils.createFile("oss", name, GenerateOss.systemcompile(system).toString());
  }
  
  public static CharSequence systemcompile(final SystemImplementation system) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("COMPONENT ");
    String _convertPoint = StringUtils.convertPoint(system.getName());
    _builder.append(_convertPoint);
    _builder.append(" system");
    _builder.newLineIfNotEmpty();
    {
      int _size = system.getAllFeatures().size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("INTERFACE");
        _builder.newLine();
        CharSequence _genFeature = FeatureTemplate.genFeature(system.getAllFeatures());
        _builder.append(_genFeature);
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _dealContract = GenerateOss.dealContract(system);
    _builder.append(_dealContract);
    _builder.append("    \t");
    _builder.newLineIfNotEmpty();
    {
      int _size_1 = system.getAllSubcomponents().size();
      boolean _greaterThan_1 = (_size_1 > 0);
      if (_greaterThan_1) {
        _builder.append("REFINEMENT");
        _builder.newLine();
        {
          EList<Subcomponent> _allSubcomponents = system.getAllSubcomponents();
          for(final Subcomponent Subcomponent : _allSubcomponents) {
            _builder.append("SUB ");
            String _name = Subcomponent.getName();
            _builder.append(_name);
            _builder.append(" : ");
            String _convertPoint_1 = StringUtils.convertPoint(Subcomponent.getSubcomponentType().getName());
            _builder.append(_convertPoint_1);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      int _size_2 = system.getAllConnections().size();
      boolean _greaterThan_2 = (_size_2 > 0);
      if (_greaterThan_2) {
        CharSequence _genConnection = ConnectionTemplate.genConnection(system.getAllConnections());
        _builder.append(_genConnection);
        _builder.append("    ");
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _dealRefinedBy = GenerateOss.dealRefinedBy(system);
    _builder.append(_dealRefinedBy);
    _builder.append("    \t");
    _builder.newLineIfNotEmpty();
    {
      int _size_3 = system.getOwnedSystemSubcomponents().size();
      boolean _greaterThan_3 = (_size_3 > 0);
      if (_greaterThan_3) {
        {
          EList<SystemSubcomponent> _ownedSystemSubcomponents = system.getOwnedSystemSubcomponents();
          for(final SystemSubcomponent subcomponent : _ownedSystemSubcomponents) {
            SubcomponentType _subcomponentType = subcomponent.getSubcomponentType();
            SystemImplementation systemImplementation = ((SystemImplementation) _subcomponentType);
            _builder.newLineIfNotEmpty();
            CharSequence _compile = GenerateOss.compile(systemImplementation);
            _builder.append(_compile);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      int _size_4 = system.getOwnedProcessSubcomponents().size();
      boolean _greaterThan_4 = (_size_4 > 0);
      if (_greaterThan_4) {
        {
          EList<ProcessSubcomponent> _ownedProcessSubcomponents = system.getOwnedProcessSubcomponents();
          for(final ProcessSubcomponent processsubcomponent : _ownedProcessSubcomponents) {
            SubcomponentType _subcomponentType_1 = processsubcomponent.getSubcomponentType();
            ProcessImplementation processImplementation = ((ProcessImplementation) _subcomponentType_1);
            _builder.newLineIfNotEmpty();
            CharSequence _genProcess = ProcessTemplate.genProcess(processImplementation);
            _builder.append(_genProcess);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence compile(final SystemImplementation system) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("COMPONENT ");
    String _convertPoint = StringUtils.convertPoint(system.getName());
    _builder.append(_convertPoint);
    _builder.newLineIfNotEmpty();
    {
      int _size = system.getAllFeatures().size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("INTERFACE");
        _builder.newLine();
        CharSequence _genFeature = FeatureTemplate.genFeature(system.getAllFeatures());
        _builder.append(_genFeature);
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _dealContract = GenerateOss.dealContract(system);
    _builder.append(_dealContract);
    _builder.append("\t\t");
    _builder.newLineIfNotEmpty();
    {
      int _size_1 = system.getAllSubcomponents().size();
      boolean _greaterThan_1 = (_size_1 > 0);
      if (_greaterThan_1) {
        _builder.append("REFINEMENT");
        _builder.newLine();
        {
          EList<Subcomponent> _allSubcomponents = system.getAllSubcomponents();
          for(final Subcomponent component : _allSubcomponents) {
            _builder.append("SUB ");
            String _name = component.getName();
            _builder.append(_name);
            _builder.append(" : ");
            String _convertPoint_1 = StringUtils.convertPoint(component.getSubcomponentType().getName());
            _builder.append(_convertPoint_1);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      int _size_2 = system.getAllConnections().size();
      boolean _greaterThan_2 = (_size_2 > 0);
      if (_greaterThan_2) {
        CharSequence _genConnection = ConnectionTemplate.genConnection(system.getAllConnections());
        _builder.append(_genConnection);
        _builder.append("    ");
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _dealRefinedBy = GenerateOss.dealRefinedBy(system);
    _builder.append(_dealRefinedBy);
    _builder.append("\t\t");
    _builder.newLineIfNotEmpty();
    {
      int _size_3 = system.getOwnedSystemSubcomponents().size();
      boolean _greaterThan_3 = (_size_3 > 0);
      if (_greaterThan_3) {
        {
          EList<SystemSubcomponent> _ownedSystemSubcomponents = system.getOwnedSystemSubcomponents();
          for(final SystemSubcomponent subcomponent : _ownedSystemSubcomponents) {
            SubcomponentType _subcomponentType = subcomponent.getSubcomponentType();
            SystemImplementation systemImplementation = ((SystemImplementation) _subcomponentType);
            _builder.newLineIfNotEmpty();
            Object _compile = GenerateOss.compile(systemImplementation);
            _builder.append(_compile);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      int _size_4 = system.getOwnedProcessSubcomponents().size();
      boolean _greaterThan_4 = (_size_4 > 0);
      if (_greaterThan_4) {
        {
          EList<ProcessSubcomponent> _ownedProcessSubcomponents = system.getOwnedProcessSubcomponents();
          for(final ProcessSubcomponent processsubcomponent : _ownedProcessSubcomponents) {
            SubcomponentType _subcomponentType_1 = processsubcomponent.getSubcomponentType();
            ProcessImplementation processImplementation = ((ProcessImplementation) _subcomponentType_1);
            _builder.newLineIfNotEmpty();
            CharSequence _genProcess = ProcessTemplate.genProcess(processImplementation);
            _builder.append(_genProcess);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("    \t");
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence dealContract(final SystemImplementation system) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<PropertyAssociation> _allPropertyAssociations = system.getAllPropertyAssociations();
      boolean _tripleNotEquals = (_allPropertyAssociations != null);
      if (_tripleNotEquals) {
        {
          EList<PropertyAssociation> _allPropertyAssociations_1 = system.getAllPropertyAssociations();
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
  
  public static CharSequence dealRefinedBy(final SystemImplementation system) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<PropertyAssociation> _allPropertyAssociations = system.getAllPropertyAssociations();
      boolean _tripleNotEquals = (_allPropertyAssociations != null);
      if (_tripleNotEquals) {
        {
          EList<PropertyAssociation> _allPropertyAssociations_1 = system.getAllPropertyAssociations();
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
    _builder.append(" ");
    _builder.newLine();
    return _builder;
  }
}
