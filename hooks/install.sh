#!/usr/bin/env bash
set -e
if [[ ! -f .git/hooks/pre-commit ]]; then
    echo "Installing KtLint pre-commit Hook"
    cd .git/hooks
    ln -s ../../hooks/ktlint-pre-commit pre-commit
    chmod +x ./pre-commit
else
    echo "ktLint pre-commit Hook already Installed"
fi
