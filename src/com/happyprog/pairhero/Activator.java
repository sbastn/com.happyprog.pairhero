package com.happyprog.pairhero;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.happyprog.pairhero"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *          the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		loadMessages(reg);
		loadStartDialog(reg);
		loadAvatars(reg);
	}

	private void loadMessages(ImageRegistry reg) {
		reg.put("blank", getImageDescriptor("icons/blank.png"));
		reg.put("green", getImageDescriptor("icons/green.png"));
		reg.put("refactoring", getImageDescriptor("icons/refactoring.png"));
		reg.put("switch-4x", getImageDescriptor("icons/switch-4x.png"));
		reg.put("switch-2x", getImageDescriptor("icons/switch-2x.png"));
		reg.put("switch", getImageDescriptor("icons/switch.png"));
	}

	private void loadStartDialog(ImageRegistry reg) {
		reg.put("logo", getImageDescriptor("icons/logo.png"));
		reg.put("div-bar", getImageDescriptor("icons/divbar.png"));
	}

	private void loadAvatars(ImageRegistry reg) {
		reg.put("explorator", getImageDescriptor("icons/explorator.png"));
		reg.put("king", getImageDescriptor("icons/king.png"));
		reg.put("robin", getImageDescriptor("icons/robin.png"));
		reg.put("monster", getImageDescriptor("icons/monster.png"));
		reg.put("no-avatar", getImageDescriptor("icons/no-avatar.png"));
	}

	public Image getImageFromKey(String key) {
		return getImageRegistry().get(key);
	}
}
