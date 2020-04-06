package aadl2ocra.template;

import aadl2ocra.template.PropertyTemplate;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.EventPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.PropertyAssociation;

@SuppressWarnings("all")
public class FeatureTemplate {
  public static CharSequence genFeature(final List<Feature> features) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Feature feature : features) {
        CharSequence _switchResult = null;
        boolean _matched = false;
        if (feature instanceof DataPort) {
          _matched=true;
          StringConcatenation _builder_1 = new StringConcatenation();
          {
            if (((((DataPort)feature).isIn() == true) && (((DataPort)feature).isOut() == false))) {
              _builder_1.append("INPUT PORT ");
              String _name = ((DataPort)feature).getName();
              _builder_1.append(_name);
              _builder_1.append(" : ");
              CharSequence _dealClassisfy = FeatureTemplate.dealClassisfy(feature);
              _builder_1.append(_dealClassisfy);
              _builder_1.newLineIfNotEmpty();
            }
          }
          {
            if (((((DataPort)feature).isIn() == false) && (((DataPort)feature).isOut() == true))) {
              _builder_1.append("OUTPUT PORT ");
              String _name_1 = ((DataPort)feature).getName();
              _builder_1.append(_name_1);
              _builder_1.append(" : ");
              CharSequence _dealClassisfy_1 = FeatureTemplate.dealClassisfy(feature);
              _builder_1.append(_dealClassisfy_1);
              _builder_1.newLineIfNotEmpty();
            }
          }
          {
            if (((((DataPort)feature).isIn() == true) && (((DataPort)feature).isOut() == true))) {
              _builder_1.append("INPUT PORT ");
              String _name_2 = ((DataPort)feature).getName();
              _builder_1.append(_name_2);
              _builder_1.append("_1 : ");
              CharSequence _dealClassisfy_2 = FeatureTemplate.dealClassisfy(feature);
              _builder_1.append(_dealClassisfy_2);
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("OUTPUT PORT ");
              String _name_3 = ((DataPort)feature).getName();
              _builder_1.append(_name_3);
              _builder_1.append("_2 : ");
              CharSequence _dealClassisfy_3 = FeatureTemplate.dealClassisfy(feature);
              _builder_1.append(_dealClassisfy_3);
              _builder_1.newLineIfNotEmpty();
            }
          }
          _switchResult = _builder_1;
        }
        if (!_matched) {
          if (feature instanceof EventPort) {
            _matched=true;
            StringConcatenation _builder_1 = new StringConcatenation();
            {
              if (((((EventPort)feature).isIn() == true) && (((EventPort)feature).isOut() == false))) {
                _builder_1.append("INPUT PORT ");
                String _name = ((EventPort)feature).getName();
                _builder_1.append(_name);
                _builder_1.append(" : event;");
                _builder_1.newLineIfNotEmpty();
              }
            }
            {
              if (((((EventPort)feature).isIn() == false) && (((EventPort)feature).isOut() == true))) {
                _builder_1.append("OUTPUT PORT ");
                String _name_1 = ((EventPort)feature).getName();
                _builder_1.append(_name_1);
                _builder_1.append(" :  event;");
                _builder_1.newLineIfNotEmpty();
              }
            }
            {
              if (((((EventPort)feature).isIn() == true) && (((EventPort)feature).isOut() == true))) {
                _builder_1.append("INPUT PORT ");
                String _name_2 = ((EventPort)feature).getName();
                _builder_1.append(_name_2);
                _builder_1.append("_1 :  event;");
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("OUTPUT PORT ");
                String _name_3 = ((EventPort)feature).getName();
                _builder_1.append(_name_3);
                _builder_1.append("_2 :  event;");
                _builder_1.newLineIfNotEmpty();
              }
            }
            _switchResult = _builder_1;
          }
        }
        if (!_matched) {
          if (feature instanceof EventDataPort) {
            _matched=true;
            StringConcatenation _builder_1 = new StringConcatenation();
            {
              if (((((EventDataPort)feature).isIn() == true) && (((EventDataPort)feature).isOut() == false))) {
                _builder_1.append("INPUT PORT ");
                String _name = ((EventDataPort)feature).getName();
                _builder_1.append(_name);
                _builder_1.append("_1 : ");
                CharSequence _dealClassisfy = FeatureTemplate.dealClassisfy(feature);
                _builder_1.append(_dealClassisfy);
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("INPUT PORT ");
                String _name_1 = ((EventDataPort)feature).getName();
                _builder_1.append(_name_1);
                _builder_1.append("_2 : event;");
                _builder_1.newLineIfNotEmpty();
              }
            }
            {
              if (((((EventDataPort)feature).isIn() == false) && (((EventDataPort)feature).isOut() == true))) {
                _builder_1.append("OUTPUT PORT ");
                String _name_2 = ((EventDataPort)feature).getName();
                _builder_1.append(_name_2);
                _builder_1.append("_1 : ");
                CharSequence _dealClassisfy_1 = FeatureTemplate.dealClassisfy(feature);
                _builder_1.append(_dealClassisfy_1);
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("OUTPUT PORT ");
                String _name_3 = ((EventDataPort)feature).getName();
                _builder_1.append(_name_3);
                _builder_1.append("_2 : event;");
                _builder_1.newLineIfNotEmpty();
              }
            }
            {
              if (((((EventDataPort)feature).isIn() == true) && (((EventDataPort)feature).isOut() == true))) {
                _builder_1.append("INPUT PORT ");
                String _name_4 = ((EventDataPort)feature).getName();
                _builder_1.append(_name_4);
                _builder_1.append("_1 : ");
                CharSequence _dealClassisfy_2 = FeatureTemplate.dealClassisfy(feature);
                _builder_1.append(_dealClassisfy_2);
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("INPUT PORT ");
                String _name_5 = ((EventDataPort)feature).getName();
                _builder_1.append(_name_5);
                _builder_1.append("_2 : event;");
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("OUTPUT PORT ");
                String _name_6 = ((EventDataPort)feature).getName();
                _builder_1.append(_name_6);
                _builder_1.append("_3 : ");
                CharSequence _dealClassisfy_3 = FeatureTemplate.dealClassisfy(feature);
                _builder_1.append(_dealClassisfy_3);
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("OUTPUT PORT ");
                String _name_7 = ((EventDataPort)feature).getName();
                _builder_1.append(_name_7);
                _builder_1.append("_4 : event;");
                _builder_1.newLineIfNotEmpty();
              }
            }
            _switchResult = _builder_1;
          }
        }
        _builder.append(_switchResult);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public static CharSequence dealClassisfy(final Feature feature) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Classifier _classifier = feature.getClassifier();
      boolean _tripleNotEquals = (_classifier != null);
      if (_tripleNotEquals) {
        {
          EList<PropertyAssociation> _allPropertyAssociations = feature.getClassifier().getAllPropertyAssociations();
          boolean _tripleNotEquals_1 = (_allPropertyAssociations != null);
          if (_tripleNotEquals_1) {
            {
              EList<PropertyAssociation> _allPropertyAssociations_1 = feature.getClassifier().getAllPropertyAssociations();
              for(final PropertyAssociation pa : _allPropertyAssociations_1) {
                CharSequence _dealProperty = PropertyTemplate.dealProperty(pa);
                _builder.append(_dealProperty);
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      } else {
        _builder.append("real;");
        _builder.newLine();
      }
    }
    return _builder;
  }
}
