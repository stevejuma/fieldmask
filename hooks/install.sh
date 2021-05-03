#!/usr/bin/env bash
set -e

dir=$(pwd)
if [[ ! -f .git/hooks/commit-msg ]]; then
    echo "Installing CI Commit Message Hook"
    cd .git/hooks
    ln -s ../../hooks/ci-commit-msg commit-msg
    chmod +x .git/hooks/commit-msg
else
    echo "CI Commit Message Hook already Installed"
fi

cd "$pwd"
if [[ ! -f .git/hooks/pre-commit ]]; then
    echo "Installing KtLint pre-commit Hook"
    cd .git/hooks
    ln -s ../../hooks/ktlint-pre-commit pre-commit
    chmod +x .git/hooks/pre-commmit
else
    echo "ktLint pre-commit Hook already Installed"
fi
