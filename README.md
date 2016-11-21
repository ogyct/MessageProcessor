#MessageProcessor

This is a demo project, it somewhat shows, what I have being doing recently on my previous job.

The main idea is simple, it is a web application with a simple jsp page. JSP has text area, which should accept some XML message and send it to servlet. Xml message is parsed using jaxb, according to its type, the right message processor is chosen. Currently Actor.xml is supported only. It makes insert, delete or update of a row in Actor table(sakila mysql DB is used for this).

Technologies used: Java, Servlet, JAXB, MySQL, Hibernate, Maven, JSP, XML, XSD, Log4j.

This is still in development, planning to implement:

- Exception handling.
- At least one more message type support.
- Jenkins continious delivery to openshift(by redhat) platform.

*Application doesn't claim to be perfect, it would take significantly more effort and time to make it really worthy.*
