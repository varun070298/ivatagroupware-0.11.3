/*
 * Copyright (c) 2001 - 2005 ivata limited.
 * All rights reserved.
 * -----------------------------------------------------------------------------
 * ivata groupware may be redistributed under the GNU General Public
 * License as published by the Free Software Foundation;
 * version 2 of the License.
 *
 * These programs are free software; you can redistribute them and/or
 * modify them under the terms of the GNU General Public License
 * as published by the Free Software Foundation; version 2 of the License.
 *
 * These programs are distributed in the hope that they will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License in the file LICENSE.txt for more
 * details.
 *
 * If you would like a copy of the GNU General Public License write to
 *
 * Free Software Foundation, Inc.
 * 59 Temple Place - Suite 330
 * Boston, MA 02111-1307, USA.
 *
 *
 * To arrange commercial support and licensing, contact ivata at
 *                  http://www.ivata.com/contact.jsp
 * -----------------------------------------------------------------------------
 * $Log: TreeSelectTag.java,v $
 * Revision 1.5  2005/04/30 13:04:11  colinmacleod
 * Fixes reverting id type from String to Integer.
 *
 * Revision 1.4  2005/04/29 02:48:14  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:32:01  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:10  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:36  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/03 16:10:12  colinmacleod
 * Changed todo comments to TODO: all caps.
 *
 * Revision 1.1  2004/09/30 15:16:03  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.3  2004/07/13 19:41:15  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:18  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:58  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/17 12:36:13  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.1.1.1  2003/10/13 20:50:13  colin
 * Restructured portal into subprojects
 *
 * Revision 1.3  2003/08/05 14:51:48  jano
 * fixing for addressBook extension
 *
 * Revision 1.2  2003/07/25 11:43:08  jano
 * we have new field MaxDepth in treeSelect TAG
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.10  2003/02/19 07:58:31  colin
 * fixed non-string properties
 *
 * Revision 1.9  2003/02/04 17:43:51  colin
 * copyright notice
 *
 * Revision 1.8  2003/02/01 12:48:31  colin
 * added style and styleClass attributes
 *
 * Revision 1.7  2003/01/28 12:57:36  colin
 * moved HTML source to theme
 * made TreeSelectClass a subclass of ControlTag
 *
 * Revision 1.6  2002/09/27 12:50:18  jano
 * bug with useJavaScript
 *
 * Revision 1.5  2002/08/27 09:09:21  colin
 * fixed selected (bug in setSelected)
 *
 * Revision 1.4  2002/06/28 13:24:20  colin
 * allowed setSelected to also take a list as a parameter
 *
 * Revision 1.3  2002/06/21 12:11:13  colin
 * restructured com.ivata.groupware.web and split into separate
 * subcategories
 *
 * Revision 1.2  2002/06/13 15:45:15  peter
 * brought over to peter, fixed bugs in webgui property-settings
 *
 * Revision 1.1  2002/06/13 07:44:07  colin
 * first version of rose model: code tidied up/added javadoc
 *
 * Revision 1.5  2002/02/03 15:24:08  colin
 * changed classname for User to PersonUser
 *
 * Revision 1.4  2002/02/02 21:23:01  colin
 * major restructuring to make the Settings class more generic
 * all default settings are now taken from the database rather
 * than
 * being hard coded in the settings class
 * settings are stored in a HashMap in settings
 *
 * Revision 1.3  2002/01/27 19:55:48  colin
 * updated the themes by removing the multiple section tags and
 * replacing them with one tag and a Properties instance in
 * com.ivata.groupware.web.theme.Theme
 *
 * Revision 1.2  2002/01/24 13:19:40  colin
 * consolidated hanlding of theme and properties tags acoss
 * webgui tag
 * library
 *
 * Revision 1.1  2002/01/20 19:28:25  colin
 * added tab and tree tags
 * implemented address book functionality
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tag.webgui.tree;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.swing.tree.TreeModel;

import com.ivata.groupware.web.tree.TreeNode;
import com.ivata.mask.util.CollectionHandling;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.web.tag.webgui.ControlTag;
import com.ivata.mask.web.theme.Theme;


/**
 * <p>Create a tree from a {@link javax.swing.tree.TreeModel
 * TreeModel}.</p>
 * <p>This tree can be displayed as a:<br/>
 * <ul>
 * <li>combo box for single selection</li>
 * <li>list control for multi-select displaying all tree nodes</li>
 * </ul>
 * </p>
 * <p>In both cases, an HTML <code>&lt;select&gt;</code> is used in
 * the
 * implementation.</p>
 * <p><strong>Tag attributes:</strong><br/>
 * <table cellpadding='2' cellspacing='5' border='0' align='center'
 * width='85%'>
 *   <tr class='TableHeadingColor'>
 *     <th>attribute</th>
 *     <th>reqd.</th>
 *     <th>param. class</th>
 *     <th width='100%'>description</th>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>controlName</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>This attribute dictates the name of the list/combo box
 * which is
 * created.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>defaultCaption</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>If you specify treeStyle as 'combo', this sets the caption
 * for the
 * default option, such as "Please choose...".</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>defaultId</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>If you specify treeStyle as 'combo', this sets the id value for the
 * default selection.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>listSize</td>
 *     <td>false</td>
 *     <td><code>Integer</code></td>
 *     <td>If you specify treeStyle as 'list', then this parameter
 * controls
 * the size of the list which is generated. The default is 15.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>model</td>
 *     <td>true</td>
 *     <td>{@link javax.swing.tree.TreeModel
 * javax.swing.tree.TreeModel}</td>
 *     <td>This model contains the data source for the tree. To use
 * any
 * datasource
 * with this tree control, you should first create a class which
 * implements
 * {@link javax.swing.tree,TreeModel TreeModel}.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>onChange</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>This attribute dictates javascript to implement in the
 * control's
 * 'onChange' attribute.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>readOnly</td>
 *     <td>false</td>
 *     <td><code>Boolean</code></td>
 *     <td>If set to <code>true</code>, then the control generated
 * will be
 * read only.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>selected</td>
 *     <td>false</td>
 *     <td><code>String</code> or
 * <code>java.util.List</code></td>
 *     <td>If you specify treeStyle as 'combo', then this attribute
 * specifies
 * the id of the tree node which is currently selected.<br/>
 * If you specify treeStyle as 'list', then this attribute specifies
 * all the ids of the tree nodes which are currently selected in the
 * form
 * of a list.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>treeName</td>
 *     <td>true</td>
 *     <td><code>String</code></td>
 *     <td>Specifies a unique identifier for this tree, which is used
 * to store
 * the state of each foler (open/closed).</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>treeStyle</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>Either 'combo' (default) or 'list', which results in a tree
 * within
 * an HTML <code>&lt;select&gt;</code> tag, as a pull-down control or
 * list
 * control respectively.<br/>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>useJavaScript</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td> If true, then the tree tab creates a control using
 * javascript rather
 * than implementing the control in HTML directly. This is only used
 * if
 * treeStyle is 'combo' or 'list'.</td>
 *   </tr>
 * </table>
 * </p>
 *
 * @since   2001-12-15
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.5 $
 */
public class TreeSelectTag extends ControlTag {
    /**
     * <p>This is the special property used to identify the location
     * of the children in the open tag.</p>
     *
     * <p><strong>Note</strong> that this has to be specified exactly, with <u>no
     * spaces</u>.</p>
     */
    static final String childrenProperty = "treeChildren";
    /**
     * <p>Property declaration for tag attribute: controlName.</p>
     */
    private String controlName;
    /**
     * <p>Property declaration for tag attribute: defaultCaption</p>
     */
    private String defaultCaption = null;
    /**
     * <p>
     * Identifer value of the default selection.
     * </p>
     */
    private String defaultValue = "";
    /**
     * <p>Property declaration for tag attribute: listSize.</p>
     */
    private Integer listSize = new Integer (15);
    /**
     * <p>Property declaration for tag attribute: maxDepth.
     * Starting at 1 - null. If null taking all children in root.</p>
     */
    private Integer maxDepth = null;
    /**
     * <p>Property declaration for tag attribute: model.</p>
     */
    private TreeModel model = null;
    /**
     * <p>Property declaration for tag attribute: onChange.</p>
     */
    private String onChange = null;
    /**
     * <p>TODO: add a comment here.</p>
     */
    private boolean readOnly;
    /**
     * <p>Property for tag attribute 'selected'.</p>
     */
    private String selected = null;
    /**
     * <p>Property for tag attribute 'selectedList'.</p>
     */
    private List selectedList = null;
    /**
     * <p>Property declaration for tag attribute: treeName.</p>
     */
    private String treeName = null;
    /**
     * <p>Property declaration for tag attribute: treeStyle.</p>
     */
    private String treeStyle = "combo";
    /**
     * <p>Property declaration for tag attribute: useJavaScript.</p>
     */
    private boolean useJavaScript = false;

    /**
     * <p>Default constructor.</p>
     *
     */
    public TreeSelectTag() {
        super();
    }

    /**
     * <p>This is the method which performs the clever stuff and actually
     * creates the tree by recursing on itself.</p>
     *
     * @param parent the parent of all the children we are going to create
     * as
     * options
     * of the select.
     * @param out the writer where all the results are written.
     * @param actualDepth is a depth of actula parent.
     * @throws JspException if there is any IOException writing to
     * <code>out</code>.
     *
     */
    private void createChildren(final Object parent,
            final JspWriter out,
            final int actualDepth) throws JspException {
        String prependString = "";
        for (int i=1; i<actualDepth; i++) {
            if (useJavaScript) {
                prependString += "- ";
            } else {
                prependString += "-&nbsp;";
            }
        }

        try {
            int totalNodes = model.getChildCount(parent);

            for (int nodeNumber = 0; nodeNumber < totalNodes; ++nodeNumber) {
                TreeNode node = (TreeNode) model.getChild(parent, nodeNumber);
                String javaSript = "javascript:document.frmFolder" + node.getId() + ".submit(  )";
                // the output depends on whether or not javajcript is used...
                String sSpaces;

                if (useJavaScript) {
                    out.println(controlName + ".options[ " + controlName + ".length ] = new Option( \"" + prependString + node.getName() + "\", " + node.getId() + " );");
                    if (isSelected(node.getId())) {
                        out.println(controlName + ".options[ " + controlName + ".length - 1 ].selected = true;");
                    }
                } else {
                    Theme theme = getTheme();
                    Properties optionProperties = CollectionHandling.splice(getProperties(), new Properties());

                    optionProperties.setProperty("value", StringHandling.getNotNull(node.getId(), "null"));
                    if (isSelected(node.getId())) {
                        optionProperties.setProperty("selected", "selected");
                    }
                    out.print(theme.parseSection("treeSelectOptionStart", optionProperties));
                    out.print(prependString + node.getName());
                    out.print(theme.parseSection("treeSelectOptionEnd", optionProperties));
                }
                // if this node has children and is open, then we have to create the children
                // but only if actula Depth is not over Max Depth
                if ( ((this.maxDepth==null) || (this.maxDepth!=null && actualDepth < this.maxDepth.intValue())) && (model.getChildCount(node) > 0)) {
                    createChildren(node, out, actualDepth+1);
                }
            }
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    /**
     * <p>This method is called when the JSP engine encounters the start
     * tag,
     * after the attributes are processed.<p>
     *
     * <p>Scripting variables (if any) have their values set here.</p>
     *
     * @return <code>SKIP_BODY</code> if this tag has no body or it
     * should be skipped, otherwise <code>EVAL_BODY_BUFFERED</code>
     * @throws JspException if there is an error retrieving the
     * navigation
     * object.
     * @throws JspException if there is an error wrting to
     * <code>out.print(
     * )</code>
     */
    public int doStartTag() throws JspException {
        super.doStartTag();
        try {
            // create the full tree ( null parent )
            JspWriter out = pageContext.getOut();

            // if we are outputting javascript, then just clear the control at this point
            if (useJavaScript) {
                out.println(controlName + ".options.length=0;");
            } else {
                // duplicate the standard properties and add to them
                Properties properties = CollectionHandling.splice(getProperties(), new Properties());

                properties.setProperty("name", controlName);
                // the good old HTML will depend on whether this select is a combo or a list...
                // if the tag is read only, put that attribute out
                if (readOnly) {
                    properties.setProperty("readOnly", "readOnly");
                }
                // if there is javascript for the onChange event, use that
                if (onChange != null) {
                    properties.setProperty("onChange", onChange);
                }

                String styleId = getStyleId();
                if (styleId != null) {
                    properties.setProperty("styleId", styleId);
                }
                // likewise css style
                String style = getStyle();
                if (style != null) {
                    properties.setProperty("style", style);
                }
                String styleClass = getStyleClass();
                if (styleClass != null) {
                    properties.setProperty("styleClass", styleClass);
                }
                TreeNode root = (TreeNode) model.getRoot();

                // if this is a list, then output the size of the list
                if (treeStyle.equalsIgnoreCase("list")) {
                    properties.setProperty("multiple", "multiple");
                    properties.setProperty("listSize", listSize.toString());
                }
                Theme theme = getTheme();

                out.print(theme.parseSection("treeSelectStart", properties));
                if (defaultCaption != null) {
                    Properties optionProperties = CollectionHandling.splice(getProperties(), new Properties());

                    optionProperties.setProperty("value", defaultValue);
                    out.print(theme.parseSection("treeSelectOptionStart", optionProperties));
                    out.print(defaultCaption);
                    out.print(theme.parseSection("treeSelectOptionEnd", optionProperties));
                }
                createChildren(root, out, 1);
                // if this is not javascript, then close the tag
                if (!useJavaScript) {
                    out.print(theme.parseSection("treeSelectEnd", properties));
                }
            }
        } catch (IOException ioException) {
            throw new JspException(
            "Error in TreeSelectTag: IOException whilst printing select.",
                ioException);
        } catch (Exception e) {
            throw new JspException("Error in TreeSelectTag: unhandled exception of class '"
                    + e.getClass().getName() + "'",
                    e);
        }
        // this tag has no body
        return SKIP_BODY;
    }

    /**
     * <p>Get the value supplied to the attribute 'controlName'.</p>
     *
     * <p>This attribute dictates the name of the list/combo box which is
     * created.</p>
     *
     * @return the value supplied to the tag attribute 'controlName'.
     *
     */
    public final String getControlName() {
        return controlName;
    }

    /**
     * <p>Get the value supplied to the attribute 'defaultCaption'.</p>
     *
     * <p>If you specify treeStyle as 'combo', this attribute sets the
     * caption
     * for the
     * default option, such as "Please choose...".</p>
     *
     * @return the value supplied to the tag attribute 'defaultCaption'.
     *
     */
    public final String getDefaultCaption() {
        return defaultCaption;
    }
    /**
     * <p>
     * Identifer value of the default selection.
     * </p>
     *
     * @return current value of defaultValue.
     */
    public final String getDefaultValue() {
        return defaultValue;
    }

    /**
     * <p>Get the value supplied to the attribute 'listSize'.</p>
     *
     * <p>If you specify treeStyle as 'list', then this parameter controls
     * the size of the list which is generated. The default is 15.</p>
     *
     * @return the value supplied to the tag attribute 'listSize'.
     *
     */
    public final Integer getListSize() {
        return listSize;
    }

    /**
     * <p>Property declaration for tag attribute: maxDepth.
     * Starting at 1 - null. If null taking all children in root.</p>
     *
     *
     * @return the current value of maxDepth.
     */
    public final Integer getMaxDepth() {
        return this.maxDepth;
    }

    /**
     * <p>Get the value supplied to the attribute 'model'.</p>
     *
     * <p>This model contains the data source for the tree. To use any
     * datasource
     * with this tree control, you should first create a class which
     * implements
     * {@link javax.swing.tree,TreeModel TreeModel}.</p>
     *
     * @return the value supplied to the tag attribute 'model'.
     *
     */
    public final TreeModel getModel() {
        return model;
    }

    /**
     * <p>Get the value supplied to the attribute 'onChange'.</p>
     *
     * <p>This attribute dictates javascript to implement in the control's
     * 'onChange'
     * attribute.</p>
     *
     * @return the value supplied to the tag attribute 'onChange'.
     *
     */
    public final String getOnChange() {
        return onChange;
    }

    /**
     * <p>Get the value supplied to the attribute 'readOnly'.</p>
     *
     * <p>If this attribute is set to <code>true</code>, then the control
     * generated will be read only.</p>
     *
     * @return the value supplied to the tag attribute 'readOnly'.
     *
     */
    public final boolean getReadOnly() {
        return readOnly;
    }

    /**
     * <p>Property for tag attribute 'selected'.</p>
     *
     * @return the current value of selected.
     */
    public final String getSelected() {
        return selected;
    }

    /**
     * <p>Property for tag attribute 'selectedList'.</p>
     *
     * @return the current value of selectedList.
     */
    public List getSelectedList() {
        return selectedList;
    }

    /**
     * <p>Get the value supplied to the attribute 'treeName'.</p>
     *
     * <p>This attribute specifies a unique identifier for this tree,
     * which is
     * used to store the state of each foler (open/closed).</p>
     *
     * @return the value supplied to the tag attribute 'treeName'.
     *
     */
    public final String getTreeName() {
        return treeName;
    }

    /**
     * <p>Get the value supplied to the attribute 'treeStyle'.</p>
     *
     * <p>This attribute can be either 'combo' (default) or 'list', which
     * results
     * in a tree within an HTML <code>&lt;select&gt;</code> tag, as a
     * pull-down
     * control or list control respectively.</p>
     *
     * @return the value supplied to the tag attribute 'treeStyle'.
     *
     */
    public final String getTreeStyle() {
        return treeStyle;
    }

    /**
     * <p>Get the value supplied to the attribute 'useJavaScript'.</p>
     *
     * <p>If true, then the tree tab creates a control using javascript
     * rather
     * than implementing the control in HTML directly. This is only used
     * if
     * treeStyle is 'combo' or 'list'.</p>
     *
     * @return the value supplied to the tag attribute 'useJavaScript'.
     *
     */
    public final boolean getUseJavaScript() {
        return useJavaScript;
    }

    /**
     * <p>Helper function to see if an entry in the mulitple choice list
     * is
     * selected or not.</p>
     *
     * @param integerParam numeric identifier
     * @return <code>true</code> if this id is in the list of selected
     * ones,
     * otherwise
     * <code>false</code>
     *
     */
    private boolean isSelected(Integer integerParam) {
        if (selectedList != null) {
            return (selectedList.indexOf(id) != -1);
        } else {
            return StringHandling.getNotNull(id).equals(selected);
        }
    }

    /**
     * <p>Set the value supplied to the attribute 'controlName'.</p>
     *
     * <p>This attribute dictates the name of the list/combo box which is
     * created.</p>
     *
     * @param controlName the new value supplied to the tag attribute
     * 'controlName'.
     *
     */
    public final void setControlName(final String controlName) {
        this.controlName = controlName;
    }

    /**
     * <p>Set the value supplied to the attribute 'defaultCaption'.</p>
     *
     * <p>If you specify treeStyle as 'combo', this attribute sets the
     * caption
     * for the
     * default option, such as "Please choose...".</p>
     *
     * @param defaultCaption the value supplied to the tag attribute
     * 'defaultCaption'.
     *
     */
    public final void setDefaultCaption(final String defaultCaption) {
        this.defaultCaption = defaultCaption;
    }
    /**
     * <p>
     * Identifer value of the default selection.
     * </p>
     *
     * @param defaultValue new value of defaultValue.
     */
    public final void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * <p>Set the value supplied to the attribute 'listSize'.</p>
     *
     * <p>If you specify treeStyle as 'list', then this parameter controls
     * the size of the list which is generated. The default is 15.</p>
     *
     * @param listSize the new value supplied to the tag attribute
     * 'listSize'.
     *
     */
    public final void setListSize(final Integer listSize) {
        this.listSize = listSize;
    }

    /**
     * <p>Property declaration for tag attribute: maxDepth.
     * Starting at 1 - null. If null taking all children in root.</p>
     *
     *
     * @param maxDepth the new value of maxDepth.
     */
    public final void setMaxDepth(final Integer maxDepth) {
        this.maxDepth = maxDepth;
    }

    /**
     * <p>Set the value supplied to the attribute 'model'.</p>
     *
     * <p>This model contains the data source for the tree. To use any
     * datasource
     * with this tree control, you should first create a class which
     * implements
     * {@link javax.swing.tree,TreeModel TreeModel}.</p>
     *
     * @param model the new value supplied to the tag attribute 'model'.
     *
     */
    public final void setModel(final TreeModel model) {
        this.model = model;
    }

    /**
     * <p>Set the value supplied to the attribute 'onChange'.</p>
     *
     * <p>This attribute dictates javascript to implement in the control's
     * 'onChange'
     * attribute.</p>
     *
     * @param onChange the new value supplied to the tag attribute
     * 'onChange'.
     *
     */
    public final void setOnChange(final String onChange) {
        this.onChange = onChange;
    }

    /**
     * <p>Set the value supplied to the attribute 'readOnly'.</p>
     *
     * <p>If this attribute is set to <code>true</code>, then the control
     * generated will be read only.</p>
     *
     * @param readOnly the new value supplied to the tag attribute
     * 'readOnly'.
     *
     */
    public final void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * <p>Property for tag attribute 'selected'.</p>
     *
     * @param selected the new value of selected.
     */
    public final void setSelected(final String selected) {
        this.selected = selected;
    }

    /**
     * <p>Property for tag attribute 'selectedList'.</p>
     *
     * @param selectedList the new value of selectedList.
     */
    public final void setSelectedList(final List selectedList) {
        this.selectedList = selectedList;
    }

    /**
     * <p>Set the value supplied to the attribute 'treeName'.</p>
     *
     * <p>This attribute specifies a unique identifier for this tree,
     * which is
     * used to store the state of each foler (open/closed).</p>
     *
     * @param treeName the new value supplied to the tag attribute
     * 'treeName'.
     *
     */
    public final void setTreeName(final String treeName) {
        this.treeName = treeName;
    }

    /**
     * <p>Set the value supplied to the attribute 'treeStyle'.</p>
     *
     * <p>This attribute can be either 'combo' (default) or 'list', which
     * results
     * in a tree within an HTML <code>&lt;select&gt;</code> tag, as a
     * pull-down
     * control or list control respectively.</p>
     *
     * @param treeStyle the new value supplied to the tag attribute
     * 'treeStyle'.
     *
     */
    public final void setTreeStyle(final String treeStyle) {
        this.treeStyle = treeStyle;
    }

    /**
     * <p>Set the value supplied to the attribute 'useJavaScript'.</p>
     *
     * <p>If true, then the tree tab creates a control using javascript
     * rather
     * than implementing the control in HTML directly. This is only used
     * if
     * treeStyle is 'combo' or 'list'.</p>
     *
     * @param useJavaScript the new value supplied to the tag attribute
     * 'useJavaScript'.
     *
     */
    public final void setUseJavaScript(final boolean useJavaScript) {
        this.useJavaScript = useJavaScript;
    }
}
