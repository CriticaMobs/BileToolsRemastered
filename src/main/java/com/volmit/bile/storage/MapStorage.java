package com.volmit.bile.storage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface MapStorage {

    public static Map<File, Long> mod = new HashMap<> ();

    public static Map<File, Long> las = new HashMap<> ();

    public static void reset(File f)
    {
        mod.put(f, f.length());
        las.put(f, f.lastModified());
    }
}
