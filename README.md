# protocol-to-protobuf
[![Project Status: WIP – Initial development is in progress, but there has not yet been a stable, usable release suitable for the public.](https://www.repostatus.org/badges/latest/wip.svg)](https://www.repostatus.org/#wip)

Description
===========
Simple protocol parser taking a single .pcap-file as input. Serialises messages from supported protocols into protocol buffers. 
Provide a method to parse from .pcap-files with [PPI](https://web.archive.org/web/20160328114748/http://www.cacetech.com/documents/PPI%20Header%20format%201.0.7.pdf)
or [Ethernet](https://ieeexplore.ieee.org/document/7428776) [link-layer header types](http://www.tcpdump.org/linktypes.html).

Main function in `za.co.jmc.reader.PcapParser` is entry point to library.

WIP status
==========
1. No protobuf implementation (yet)
2. No secondary protocol parsing, other than PCAP file reader and PPI packet parsing
3. No support for Ethernet Frame link-layer type


Usage
==========
1. compile using `mvn package`
2. in `target` directory, execute `java -jar protocol-to-protobuf-0.0.1.jar <filename>`

Contents
==========

1. za.co.jmc.pcap contains all .pcap utilities and accessors
2. za.co.jmc.reader contains main parsing classes (and should be entry point to discovering this repo)
3. za.co.jmc.util contains miscellaneous utilities
4. za.co.jmc.protocol contains protocol-specific parsing implementations

Versioning
==========
Given a version number MAJOR.MINOR.PATCH, increment the:
1. MAJOR version when you make incompatible API changes,
2. MINOR version when you add functionality in a backwards-compatible manner, and
3. PATCH version when you make backwards-compatible bug fixes.
