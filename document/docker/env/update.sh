#!/bin/sh
# 配置更新
file_path=$1
check_time=$2
whether_changed(){
    file_old_stat=$(stat -c%Y "${file_path}")
    while true ; do
        sleep "${check_time}"
        file_new_stat="$(stat -c%Y "${file_path}")"
        if [ "${file_new_stat}" -gt "${file_old_stat}" ]; then
            echo "$(stat -c%y "${file_path}") File ${file_path} has modified"  > modified.log;
            nginx -s reload
            file_old_stat=${file_new_stat};
        fi
    done
}
whether_changed
