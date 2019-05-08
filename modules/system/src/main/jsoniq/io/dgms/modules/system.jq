jsoniq version "1.0";

(:
 : بسم الله الرحمن الرحيم
 :
 : In the name of Allah, the Most Compassionate, the Most Merciful
 :
 : This file is part of Unity DGMS <https://www.dgms.io/>
 :
 : Unity DGMS is free software; redistribution and use in source and binary
 : forms, with or without modification, are permitted provided that the
 : following conditions are met:
 :
 : 1. Redistributions of source code must retain the above notice, this list of
 :    conditions and the following disclaimer.
 :
 : 2. Redistributions in binary form must reproduce the above notice, this list
 :    of conditions and the following disclaimer in the documentation and/or
 :    other materials provided with the distribution.
 :
 : THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHORS DISCLAIM ALL WARRANTIES
 : WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 : MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
 : SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 : WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 : OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 : CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 :)

module namespace system = "http://dgms.io/modules/system";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

declare %public %an:nondeterministic %an:variadic function system:abort() external;

declare %public %an:nondeterministic %an:variadic function system:access() external;

declare %public %an:nondeterministic %an:variadic function system:chdir() external;

declare %public %an:nondeterministic %an:variadic function system:chflags() external;

declare %public %an:nondeterministic %an:variadic function system:chmod() external;

declare %public %an:nondeterministic %an:variadic function system:chown() external;

declare %public %an:nondeterministic %an:variadic function system:chroot() external;

declare %public %an:nondeterministic %an:variadic function system:close() external;

declare %public %an:nondeterministic %an:variadic function system:cpu-count() external;

declare %public %an:nondeterministic %an:variadic function system:ctermid() external;

declare %public %an:nondeterministic %an:variadic function system:dup() external;

declare %public %an:nondeterministic %an:variadic function system:dup2() external;

declare %public %an:nondeterministic %an:variadic function system:execl() external;

declare %public %an:nondeterministic %an:variadic function system:execle() external;

declare %public %an:nondeterministic %an:variadic function system:execlp() external;

declare %public %an:nondeterministic %an:variadic function system:execlpe() external;

declare %public %an:nondeterministic %an:variadic function system:execv() external;

declare %public %an:nondeterministic %an:variadic function system:execve() external;

declare %public %an:nondeterministic %an:variadic function system:execvp() external;

declare %public %an:nondeterministic %an:variadic function system:execvpe() external;

declare %public %an:nondeterministic %an:variadic function system:exit() external;

declare %public %an:nondeterministic %an:variadic function system:fchdir() external;

declare %public %an:nondeterministic %an:variadic function system:fchmod() external;

declare %public %an:nondeterministic %an:variadic function system:fchown() external;

declare %public %an:nondeterministic %an:variadic function system:fdatasync() external;

declare %public %an:nondeterministic %an:variadic function system:fdopen() external;

declare %public %an:nondeterministic %an:variadic function system:fork() external;

declare %public %an:nondeterministic %an:variadic function system:forkpty() external;

declare %public %an:nondeterministic %an:variadic function system:fpathconf() external;

declare %public %an:nondeterministic %an:variadic function system:fsdecode() external;

declare %public %an:nondeterministic %an:variadic function system:fsencode() external;

declare %public %an:nondeterministic %an:variadic function system:fspath() external;

declare %public %an:nondeterministic %an:variadic function system:fstat() external;

declare %public %an:nondeterministic %an:variadic function system:fstatvfs() external;

declare %public %an:nondeterministic %an:variadic function system:fsync() external;

declare %public %an:nondeterministic %an:variadic function system:ftruncate() external;

declare %public %an:nondeterministic %an:variadic function system:fwalk() external;

declare %public %an:nondeterministic %an:variadic function system:getcwd() external;

declare %public %an:nondeterministic %an:variadic function system:getcwdb() external;

declare %public %an:nondeterministic %an:variadic function system:getegid() external;

declare %public %an:nondeterministic %an:variadic function system:getenv() external;

declare %public %an:nondeterministic %an:variadic function system:getenvb() external;

declare %public %an:nondeterministic %an:variadic function system:geteuid() external;

declare %public %an:nondeterministic %an:variadic function system:getgid() external;

declare %public %an:nondeterministic %an:variadic function system:getgrouplist() external;

declare %public %an:nondeterministic %an:variadic function system:getgroups() external;

declare %public %an:nondeterministic %an:variadic function system:getloadavg() external;

declare %public %an:nondeterministic %an:variadic function system:getlogin() external;

declare %public %an:nondeterministic %an:variadic function system:getpgid() external;

declare %public %an:nondeterministic %an:variadic function system:getpgrp() external;

declare %public %an:nondeterministic %an:variadic function system:getpid() external;

declare %public %an:nondeterministic %an:variadic function system:getppid() external;

declare %public %an:nondeterministic %an:variadic function system:getpriority() external;

declare %public %an:nondeterministic %an:variadic function system:getrandom() external;

declare %public %an:nondeterministic %an:variadic function system:getresgid() external;

declare %public %an:nondeterministic %an:variadic function system:getresuid() external;

declare %public %an:nondeterministic %an:variadic function system:getsid() external;

declare %public %an:nondeterministic %an:variadic function system:getuid() external;

declare %public %an:nondeterministic %an:variadic function system:initgroups() external;

declare %public %an:nondeterministic %an:variadic function system:isatty() external;

declare %public %an:nondeterministic %an:variadic function system:kill() external;

declare %public %an:nondeterministic %an:variadic function system:killpg() external;

declare %public %an:nondeterministic %an:variadic function system:lchflags() external;

declare %public %an:nondeterministic %an:variadic function system:lchmod() external;

declare %public %an:nondeterministic %an:variadic function system:lchown() external;

declare %public %an:nondeterministic %an:variadic function system:link() external;

declare %public %an:nondeterministic %an:variadic function system:listdir() external;

declare %public %an:nondeterministic %an:variadic function system:lockf() external;

declare %public %an:nondeterministic %an:variadic function system:lseek() external;

declare %public %an:nondeterministic %an:variadic function system:lstat() external;

declare %public %an:nondeterministic %an:variadic function system:major() external;

declare %public %an:nondeterministic %an:variadic function system:makedev() external;

declare %public %an:nondeterministic %an:variadic function system:makedirs() external;

declare %public %an:nondeterministic %an:variadic function system:minor() external;

declare %public %an:nondeterministic %an:variadic function system:mkdir() external;

declare %public %an:nondeterministic %an:variadic function system:mkfifo() external;

declare %public %an:nondeterministic %an:variadic function system:mknod() external;

declare %public %an:nondeterministic %an:variadic function system:name() external;

declare %public %an:nondeterministic %an:variadic function system:nice() external;

declare %public %an:nondeterministic %an:variadic function system:open() external;

declare %public %an:nondeterministic %an:variadic function system:openpty() external;

declare %public %an:nondeterministic %an:variadic function system:pathconf() external;

declare %public %an:nondeterministic %an:variadic function system:pipe() external;

declare %public %an:nondeterministic %an:variadic function system:pipe2() external;

declare %public %an:nondeterministic %an:variadic function system:plock() external;

declare %public %an:nondeterministic %an:variadic function system:popen() external;

declare %public %an:nondeterministic %an:variadic function system:pread() external;

declare %public %an:nondeterministic %an:variadic function system:preadv() external;

declare %public %an:nondeterministic %an:variadic function system:putenv() external;

declare %public %an:nondeterministic %an:variadic function system:pwrite() external;

declare %public %an:nondeterministic %an:variadic function system:pwritev() external;

declare %public %an:nondeterministic %an:variadic function system:read() external;

declare %public %an:nondeterministic %an:variadic function system:readlink() external;

declare %public %an:nondeterministic %an:variadic function system:readv() external;

declare %public %an:nondeterministic %an:variadic function system:remove() external;

declare %public %an:nondeterministic %an:variadic function system:removedirs() external;

declare %public %an:nondeterministic %an:variadic function system:rename() external;

declare %public %an:nondeterministic %an:variadic function system:renames() external;

declare %public %an:nondeterministic %an:variadic function system:replace() external;

declare %public %an:nondeterministic %an:variadic function system:rmdir() external;

declare %public %an:nondeterministic %an:variadic function system:scandir() external;

declare %public %an:nondeterministic %an:variadic function system:setegid() external;

declare %public %an:nondeterministic %an:variadic function system:seteuid() external;

declare %public %an:nondeterministic %an:variadic function system:setgid() external;

declare %public %an:nondeterministic %an:variadic function system:setgroups() external;

declare %public %an:nondeterministic %an:variadic function system:setpgid() external;

declare %public %an:nondeterministic %an:variadic function system:setpgrp() external;

declare %public %an:nondeterministic %an:variadic function system:setpriority() external;

declare %public %an:nondeterministic %an:variadic function system:setregid() external;

declare %public %an:nondeterministic %an:variadic function system:setresgid() external;

declare %public %an:nondeterministic %an:variadic function system:setresuid() external;

declare %public %an:nondeterministic %an:variadic function system:setreuid() external;

declare %public %an:nondeterministic %an:variadic function system:setsid() external;

declare %public %an:nondeterministic %an:variadic function system:setuid() external;

declare %public %an:nondeterministic %an:variadic function system:spawnl() external;

declare %public %an:nondeterministic %an:variadic function system:spawnle() external;

declare %public %an:nondeterministic %an:variadic function system:spawnlp() external;

declare %public %an:nondeterministic %an:variadic function system:spawnlpe() external;

declare %public %an:nondeterministic %an:variadic function system:spawnv() external;

declare %public %an:nondeterministic %an:variadic function system:spawnve() external;

declare %public %an:nondeterministic %an:variadic function system:spawnvp() external;

declare %public %an:nondeterministic %an:variadic function system:spawnvpe() external;

declare %public %an:nondeterministic %an:variadic function system:startfile() external;

declare %public %an:nondeterministic %an:variadic function system:stat() external;

declare %public %an:nondeterministic %an:variadic function system:statvfs() external;

declare %public %an:nondeterministic %an:variadic function system:symlink() external;

declare %public %an:nondeterministic %an:variadic function system:sync() external;

declare %public %an:nondeterministic %an:variadic function system:system() external;

declare %public %an:nondeterministic %an:variadic function system:times() external;

declare %public %an:nondeterministic %an:variadic function system:truncate() external;

declare %public %an:nondeterministic %an:variadic function system:ttyname() external;

declare %public %an:nondeterministic %an:variadic function system:umask() external;

declare %public %an:nondeterministic %an:variadic function system:uname() external;

declare %public %an:nondeterministic %an:variadic function system:unlink() external;

declare %public %an:nondeterministic %an:variadic function system:unsetenv() external;

declare %public %an:nondeterministic %an:variadic function system:urandom() external;

declare %public %an:nondeterministic %an:variadic function system:utime() external;

declare %public %an:nondeterministic %an:variadic function system:wait() external;

declare %public %an:nondeterministic %an:variadic function system:wait3() external;

declare %public %an:nondeterministic %an:variadic function system:wait4() external;

declare %public %an:nondeterministic %an:variadic function system:waitid() external;

declare %public %an:nondeterministic %an:variadic function system:waitpid() external;

declare %public %an:nondeterministic %an:variadic function system:walk() external;

declare %public %an:nondeterministic %an:variadic function system:write() external;

declare %public %an:nondeterministic %an:variadic function system:writev() external;
