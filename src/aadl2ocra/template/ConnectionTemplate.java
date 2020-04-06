package aadl2ocra.template;

import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.osate.aadl2.Connection;

@SuppressWarnings("all")
public class ConnectionTemplate {
  public static CharSequence genConnection(final List<Connection> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Connection connection : connections) {
        {
          if (((connection.getSource().getContext() != null) && (connection.getDestination().getContext() == null))) {
            _builder.append("CONNECTION ");
            String _name = connection.getDestination().getConnectionEnd().getName();
            _builder.append(_name);
            _builder.append(" := ");
            String _name_1 = connection.getSource().getContext().getName();
            _builder.append(_name_1);
            _builder.append(".");
            String _name_2 = connection.getSource().getConnectionEnd().getName();
            _builder.append(_name_2);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          if (((connection.getSource().getContext() == null) && (connection.getDestination().getContext() != null))) {
            _builder.append("CONNECTION ");
            String _name_3 = connection.getDestination().getContext().getName();
            _builder.append(_name_3);
            _builder.append(".");
            String _name_4 = connection.getDestination().getConnectionEnd().getName();
            _builder.append(_name_4);
            _builder.append(" := ");
            String _name_5 = connection.getSource().getConnectionEnd().getName();
            _builder.append(_name_5);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          if (((connection.getSource().getContext() != null) && (connection.getDestination().getContext() != null))) {
            _builder.append("CONNECTION ");
            String _name_6 = connection.getDestination().getContext().getName();
            _builder.append(_name_6);
            _builder.append(".");
            String _name_7 = connection.getDestination().getConnectionEnd().getName();
            _builder.append(_name_7);
            _builder.append(" := ");
            String _name_8 = connection.getSource().getContext().getName();
            _builder.append(_name_8);
            _builder.append(".");
            String _name_9 = connection.getSource().getConnectionEnd().getName();
            _builder.append(_name_9);
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
}
