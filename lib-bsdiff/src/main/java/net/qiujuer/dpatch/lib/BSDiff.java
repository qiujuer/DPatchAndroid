package net.qiujuer.dpatch.lib;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */

public final class BSDiff {
    public static native int diff(String oldPath, String newPath, String patchPath);

    public static native int patch(String oldPath, String newPath, String patchPath);
}
