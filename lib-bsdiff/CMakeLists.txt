cmake_minimum_required(VERSION 3.4.1)

add_library( # Sets the name of the library.
             bzlib-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/bzlib/blocksort.c
             src/main/cpp/bzlib/bzip2.c
             # src/main/cpp/bzlib/bzip2recover.c
             src/main/cpp/bzlib/bzlib.c
             src/main/cpp/bzlib/compress.c
             src/main/cpp/bzlib/crctable.c
             src/main/cpp/bzlib/decompress.c
             # src/main/cpp/bzlib/dlltest.c
             src/main/cpp/bzlib/huffman.c
             # src/main/cpp/bzlib/mk251.c
             src/main/cpp/bzlib/randtable.c
             # src/main/cpp/bzlib/spewG.c
             # src/main/cpp/bzlib/unzcrash.c
             )

add_library( # Sets the name of the library.
             bsdiff-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/bslib/bsdiff.c
             src/main/cpp/bslib/bspatch.c)

include_directories(${CMAKE_SOURCE_DIR}/bzlib-lib ${CMAKE_SOURCE_DIR}/bsdiff-lib)

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.cpp)


find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log)

target_link_libraries(bsdiff-lib bzlib-lib)
target_link_libraries(native-lib bsdiff-lib)
target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib})