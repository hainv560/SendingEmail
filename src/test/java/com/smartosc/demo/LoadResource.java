package com.smartosc.demo;

import com.smartosc.demo.common.Constants;
import com.smartosc.demo.util.ResourceBundleUtil;

/**
 * Created by smartosc on 5/5/2016.
 */
public class LoadResource {
    public static void main(String[] args) {
        System.out.println(ResourceBundleUtil.getInstance().loadResource(Constants.EMAIL_ADDRESS_PARAM));
    }
}
