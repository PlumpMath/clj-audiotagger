## clj-audiotagger

[![Clojars Project](https://img.shields.io/clojars/v/clj-audiotagger.svg)](https://clojars.org/clj-audiotagger)

Clojure library designed to make it easy to manipulate/cleanup bad mp3 tag with UTF-8 support.

Design with simplicity in mind so out of the box it can do the following:

- Set the file name as title of the mp3 track
- Set the track index to current position in the list e.g. track/track-total based on the current position
- Set common tags shared by all the files (e.g. same artist, album, genre, etc)

Built on top of the two useful library

- [claudio][] (Clojure)
- [jaudiotagger][] (Java)

## Basic Usage

Will be provided the easy way to run this later for now please see below.

## Development

Available via [clojars.org/clj-audiotagger](https://clojars.org/clj-audiotagger)

- Leiningen

```clojure
[clj-audiotagger "0.1.4-SNAPSHOT"]
```

- Gradle

```
compile "clj-audiotagger:clj-audiotagger:0.1.4-SNAPSHOT"
```

- Maven

```xml
<dependency>
  <groupId>clj-audiotagger</groupId>
  <artifactId>clj-audiotagger</artifactId>
  <version>0.1.4-SNAPSHOT</version>
</dependency>
```

### Pre-requisite

- Java SDK
- Clojure
- Leiningen

### Installation

- Clone the repository

```sh
# clone the repository
mkdir -p ~/projects

# clone the repository
git clone git@github.com:agilecreativity/clj-audiotagger.git ~/projects

# Generate the standalone binary (to be installed to `~/bin`)
mkdir -p ~/bin

# Now build the standalone executable using [lein-bin]
lein bin
```

You should get output like the following:

```
$lein bin
Compiling com.agilecreativity.clj_audiotagger.util
Compiling com.agilecreativity.clj_audiotagger.main
Compiling com.agilecreativity.clj_audiotagger.cli
Created /home/bchoomnuan/projects/clj-audiotagger/target/clj-audiotagger-0.1.3-SNAPSHOT.jar
Created /home/bchoomnuan/clj-audiotagger/target/clj-audiotagger-0.1.3-SNAPSHOT-standalone.jar
Creating standalone executable: /home/bchoomnuan/projects/clj-audiotagger/target/clj-audiotagger
Copying binary to #object[java.io.File 0x3e8799f /home/bchoomnuan/bin]
```

Note that the executable is called `clj-audiotagger` and is automatically copied to `~/bin/clj-audiotagger`

### Basic Usage

Assume that the `~/bin` is in your `$PATH`

```sh
# Run the command without any options will print default usage

~/bin/clj-audiotagger`

# Or if ~/bin is your executable path you could just type
clj-audiotagger
```

Which should produce something like:

```
Update mp3 id3 tag using simple rules.

Usage: clj_audiotagger [options]
  -b, --base-dir DIR             .  The staring directory
  -f, --file-name-as-title          Use file name as title
  -t, --position-as-track-order     Use position as track order
  -u, --set-shared-tags             Update shared tags
  -c, --cover COVER_FILE            The shared cover file that will be updated to
  -h, --help

Options:

--base-dir DIR starting directory
--cover COVER_FILE the shared cover file if any
--filename-as-title
--position-as-track-order
--set-shared-tags tags
  title   TITLE
  album   ALBUM
  artist  ARTIST
  genre   GENRE
  year    YEAR
  comment COMMENT

Please refer to the manual page for more information.
```

### Sample Usage

- To rename all the mp3 files to the filename try

```sh
clj-audiotagger --base-dir ~/media/mp3s --file-name-as-title
```

- To update the play index try

```sh
# long version
clj-audiotagger --base-dir ~/media/mp3s --position-as-track-order

# Or short version
clj-audiotagger -b ~/media/mp3s -t
```

- To update the common attributes of a given list of files

e.g. Good use-case if have the media sharing the same type of attribute (artist, album, year)

```sh
# Using short version of the command
clj-audiotagger -b ~/media/mp3s -u album "Metallica Greatest Hits" artist "Metallica" comment "My fav band"
```

for list of the attribute please see [claudio][] for attribute you can set.

The common values are:

```
title   TITLE
album   ALBUM
artist  ARTIST
genre   GENRE
year    YEAR
comment COMMENT
```

- To update the cover image for all files in a given directory (both jpg and png are supported)

```sh
# If you have the png file
clj-audiotagger -b ~/media/mp3s \
                -u album "Metallica Greatest Hits" artist "Metallica" comment "My fav band" \
                -c music/metallica-cover.png

# If you have the jpg file
clj-audiotagger -b ~/media/mp3s \
                -u album "Continuum" artist "John Mayer" \
                -c music/john-mayer.jpg
```

## Roadmaps/Todos

- [x] Add documentation on how to run it with [lein-bin][]
- [x] Add the ability to update of cover image for a file

## Useful Links

Some links I found useful while developing this library

- [claudio][] - read/write MP3 ID3 tags with Clojure
- [jaudiotagger][] - most recent version of the original Java library
- [fs][] - file system library by [Anthony Grimes](https://github.com/Raynes)
- [Fleetdb project](https://github.com/mmcgrana/fleetdb/blob/master/project.clj)
- [Mixing Java and Clojure project](http://hypirion.com/musings/advanced-intermixing-java-clj)
- [Good example on Java interop with Clojure](http://www.braveclojure.com/java/)
- [Working with file in Clojure](http://clojure-doc.org/articles/cookbooks/files_and_directories.html)
- [Java interop with Clojure](http://www.braveclojure.com/java/)
- [Mp3 cover art](http://www.richardfarrar.com/embedding-album-art-in-mp3-files/)
- [Random links related to embedded cover art](http://blog.magnatune.com/2012/06/album-art-now-embedded-in-our-mp3-files.html)
- [sample media with cover art](http://download.wavetlan.com/SVV/AlbumArt/index.html)
- [Good way to list Java's medthod from Clojure's REPL](http://stackoverflow.com/questions/5821286/how-can-i-get-the-methods-of-a-java-class-from-clojure)
- [List of PRs related to jaudiotagger](https://bitbucket.org/ijabz/jaudiotagger/issues?status=new&status=open)
- [List of GUI player for Arch Linux](https://wiki.archlinux.org/index.php/List_of_applications/Multimedia#GUI_players)

## License

Copyright © 2016 Burin Choomnuan

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[claudio]: https://github.com/pandeiro/claudio
[jaudiotagger]: https://bitbucket.org/ijabz/jaudiotagger/src
[fs]: https://github.com/Raynes/fs
[lein-bin]: https://github.com/Raynes/lein-bin
